package com.example.maktab.module.campaign.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.campaign.dto.CampaignDTO
import com.example.maktab.module.campaign.dto.CreateCampaignRequestDTO
import com.example.maktab.module.campaign.dto.FilterCampaignRequestDTO
import com.example.maktab.module.campaign.dto.UpdateCampaignRequestDTO
import com.example.maktab.module.campaign.entity.CampaignEntity
import com.example.maktab.module.campaign.enums.CampaignStatus
import com.example.maktab.module.campaign.mapper.CampaignMapper
import com.example.maktab.module.campaign.repository.CampaignRepository
import com.example.maktab.module.campaign.specification.CampaignSpecification
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.tag.entity.TagEntity
import com.example.maktab.module.tag.service.TagService
import com.example.maktab.module.user.entity.UserEntity
import jakarta.persistence.EntityManager
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignService(
    private val entityManager: EntityManager,
    private val campaignRepository: CampaignRepository,
    private val campaignMapper: CampaignMapper,
    private val tagService: TagService,
    private val campaignMemberService: CampaignMemberService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun createCampaign(createDto: CreateCampaignRequestDTO, userId: String): CampaignDTO {
        val user = entityManager.getReference(UserEntity::class.java, userId)
        val category = entityManager.getReference(CategoryEntity::class.java, createDto.categoryId)
        val tags = tagService.findAllByKeys(createDto.tagIds)

        // * Validate required resources
        this.validateCampaignRequiredResources(tags)

        val campaign = this.saveCampaign(createDto.let {
            CampaignEntity(
                title = it.title,
                description = it.description,
                startAt = it.startAt,
                finishAt = it.finishAt,
                currentMemberCount = 0,
                maxMemberCount = createDto.maxMemberCount,
                status = CampaignStatus.PENDING,
                owner = user,
                category = category,
                tags = tags.toMutableList()
            )
        })

        campaignMemberService.addMemberIntoCampaign(campaign, user, isOwner = true)

        logger.info("Campaign created with id ${campaign.id}")

        return campaignMapper.toDto(campaign)
    }

    @Transactional(readOnly = true)
    fun getAllCampaigns(filterDto: FilterCampaignRequestDTO, page: Pageable): Page<CampaignDTO> {
        val filter = CampaignSpecification.filter(filterDto)
        val campaigns = campaignRepository.findAll(filter, page)

        return campaigns.map { campaignMapper.toDto(it) }
    }

    @Transactional(readOnly = true)
    fun getCampaignById(id: String): CampaignDTO {
        val campaign = this.findByIdOrThrow(id)

        return campaignMapper.toDto(campaign)
    }

    @Transactional(readOnly = true)
    fun findByIdOrThrow(id: String): CampaignEntity {
        return campaignRepository.findById(id).orElseThrow { ApiError.NotFound("Invalid Campaign Id") }
    }

    @Transactional
    fun updateCampaign(id: String, updateDto: UpdateCampaignRequestDTO, requestUserId: String? = null): CampaignDTO {
        val campaign = this.findByIdOrThrow(id)

        // * Validate campaign ownership for when requester user id existed
        requestUserId?.let {
            this.validateOwnershipOfCampaign(campaign, it)
        }

        val category = entityManager.getReference(CategoryEntity::class.java, updateDto.categoryId)
        val tags = tagService.findAllByKeys(updateDto.tagIds)

        // * Validate required resources
        this.validateCampaignRequiredResources(tags)

        campaign.apply {
            this.title = updateDto.title
            this.description = updateDto.description
            this.startAt = updateDto.startAt
            this.finishAt = updateDto.finishAt
            this.category = category
            this.tags = tags.toMutableList()
        }

        saveCampaign(campaign)

        logger.info("The campaign id $id updated successfully.")

        return campaignMapper.toDto(campaign)
    }

    @Transactional
    fun deleteCampaign(id: String, requestUserId: String? = null) {
        val campaign = this.findByIdOrThrow(id)

        // * Validate campaign ownership for when requester user id existed
        requestUserId?.let {
            this.validateOwnershipOfCampaign(campaign, it)
        }

        campaignRepository.delete(campaign)

        logger.info("The campaign id $id removed successfully")
    }

    @Transactional
    fun saveCampaign(campaign: CampaignEntity): CampaignEntity = campaignRepository.save(campaign)

    @Transactional
    fun joinIntoCampaign(campaignId: String, userId: String) {
        val campaign = this.findByIdOrThrow(campaignId)
        val user = entityManager.getReference(UserEntity::class.java, userId)

        // * Validate joining policy (member count)
        this.validateCampaignJoiningPolicy(campaign)

        campaignMemberService.addMemberIntoCampaign(campaign, user)
        campaign.currentMemberCount++

        this.saveCampaign(campaign)
    }

    @Transactional
    fun leaveFromCampaign(campaignId: String, userId: String) {
        val campaign = this.findByIdOrThrow(campaignId)
        val user = entityManager.getReference(UserEntity::class.java, userId)

        // * Validate leaving policy (ownership)
        this.validateCampaignLeavingPolicy(campaign, user)

        campaignMemberService.removeMemberFromCampaign(campaign, user)
        campaign.currentMemberCount--

        this.saveCampaign(campaign)
    }

    fun validateCampaignRequiredResources(
        tags: List<TagEntity>
    ) {
        if (tags.isEmpty()) throw ApiError.BadRequest("At least one tag is required")
    }

    fun validateOwnershipOfCampaign(campaign: CampaignEntity, ownerId: String?) {
        if (ownerId != null && campaign.ownerId != ownerId) throw ApiError.Forbidden("You do not have permission to access this campaign")
    }

    fun validateCampaignJoiningPolicy(campaign: CampaignEntity) {
        if (campaign.currentMemberCount + 1 > campaign.maxMemberCount) throw ApiError.Conflict("The campaign is completed (full)")
    }

    fun validateCampaignLeavingPolicy(campaign: CampaignEntity, user: UserEntity) {
        if (campaign.ownerId == user.id) throw ApiError.Conflict("Your the owner of the camping, you can't leave")
    }
}