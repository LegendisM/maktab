package com.example.maktab.module.campaign.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.campaign.dto.member.CampaignMemberDTO
import com.example.maktab.module.campaign.dto.member.UpdateCampaignMemberRequestDTO
import com.example.maktab.module.campaign.entity.CampaignEntity
import com.example.maktab.module.campaign.entity.CampaignMemberEntity
import com.example.maktab.module.campaign.mapper.CampaignMemberMapper
import com.example.maktab.module.campaign.repository.CampaignMemberRepository
import com.example.maktab.module.campaign.specification.CampaignMemberSpecification
import com.example.maktab.module.user.entity.UserEntity
import jakarta.persistence.EntityManager
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Lazy
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@Service
class CampaignMemberService(
    private val campaignMemberRepository: CampaignMemberRepository,
    private val campaignMemberMapper: CampaignMemberMapper,
    @Lazy private val campaignService: CampaignService,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun addMemberIntoCampaign(
        campaign: CampaignEntity,
        user: UserEntity,
        isOwner: Boolean = false
    ): CampaignMemberEntity {
        val isExists = this.existMemberOfCampaignByUserId(campaign.id!!, user.id!!)

        // * Prevent of already joined users
        if (isExists) throw ApiError.Conflict("The user has already joined this campaign")

        val campaignMember = this.saveCampaignMember(
            CampaignMemberEntity(
                isOwner = isOwner,
                user = user,
                campaign = campaign
            )
        )

        logger.info("The user ${user.id} joined into campaign ${campaign.id} with member id ${campaignMember.id}")

        return campaignMember
    }

    @Transactional(readOnly = true)
    fun getAllCampaignMembers(campaignId: String, requestUserId: String? = null): List<CampaignMemberDTO> {
        val campaign = campaignService.findByIdOrThrow(campaignId)

        // * Validate campaign membership for when requester user id existed
        requestUserId?.let {
            this.validateTheUserIsMemberOfCampaign(campaign.id!!, it)
        }

        val filter = CampaignMemberSpecification.filterByCampaignId(campaignId)
        val campaignMembers = campaignMemberRepository.findAll(filter)

        return campaignMemberMapper.toDtos(campaignMembers)
    }

    @Transactional(readOnly = true)
    fun findByIdOrThrow(id: String): CampaignMemberEntity {
        return campaignMemberRepository.findById(id).orElseThrow { ApiError.NotFound("Invalid Campaign Member Id") }
    }

    @Transactional(readOnly = true)
    fun findMemberOfCampaignById(campaignId: String, memberId: String): CampaignMemberEntity {
        val filter = CampaignMemberSpecification.filter(campaignId = campaignId, memberId = memberId)

        return campaignMemberRepository.findOne(filter).orElseThrow {
            ApiError.NotFound("It is not a member of this campaign")
        }
    }

    @Transactional(readOnly = true)
    fun findMemberOfCampaignByUserId(campaignId: String, userId: String): CampaignMemberEntity {
        val filter = CampaignMemberSpecification.filter(campaignId = campaignId, userId = userId)

        return campaignMemberRepository.findOne(filter).orElseThrow {
            ApiError.Forbidden("You aren't member of this campaign")
        }
    }

    @Transactional(readOnly = true)
    fun existMemberOfCampaignByUserId(campaignId: String, userId: String): Boolean {
        val filter = CampaignMemberSpecification.filter(campaignId = campaignId, userId = userId)

        return campaignMemberRepository.exists(filter)
    }

    @Transactional
    fun updateCampaignMember(
        campaignId: String,
        memberId: String,
        updateDto: UpdateCampaignMemberRequestDTO,
        requestUserId: String?
    ) {
        // * Validate campaign management access for when requester user id existed
        requestUserId?.let {
            val requesterCampaignMember = this.findMemberOfCampaignByUserId(campaignId, requestUserId)
            this.validateTheMemberHasAccessForManagement(requesterCampaignMember)
        }

        val campaignMember = this.findMemberOfCampaignById(campaignId, memberId)

        campaignMember.apply {
            updateDto.isModerator?.let {
                this.isModerator = it
            }

            updateDto.score?.let {
                this.score = it
            }
        }

        this.saveCampaignMember(campaignMember)
    }

    @Transactional
    fun removeMemberFromCampaign(campaign: CampaignEntity, user: UserEntity) {
        val campaignMember = this.findMemberOfCampaignByUserId(campaign.id!!, user.id!!)

        campaignMemberRepository.delete(campaignMember)
    }

    @Transactional
    fun saveCampaignMember(campaignMember: CampaignMemberEntity): CampaignMemberEntity {
        return campaignMemberRepository.save(campaignMember)
    }

    @Transactional(readOnly = true)
    fun validateTheUserIsMemberOfCampaign(campaignId: String, userId: String) {
        val isExists = this.existMemberOfCampaignByUserId(campaignId, userId)

        if (!isExists) throw ApiError.Forbidden("You aren't member of this campaign")
    }

    fun validateTheMemberHasAccessForManagement(campaignMember: CampaignMemberEntity) {
        if (!campaignMember.isOwner && !campaignMember.isModerator) throw ApiError.Forbidden("You don't have enough permission for management")
    }
}