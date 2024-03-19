package com.example.maktab.module.campaign.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.campaign.dto.CampaignDTO
import com.example.maktab.module.campaign.dto.CreateCampaignDTO
import com.example.maktab.module.campaign.entity.CampaignEntity
import com.example.maktab.module.campaign.mapper.CampaignMapper
import com.example.maktab.module.campaign.repository.CampaignRepository
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.category.service.CategoryService
import com.example.maktab.module.tag.entity.TagEntity
import com.example.maktab.module.tag.service.TagService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignService(
    private val campaignRepository: CampaignRepository,
    private val campaignMapper: CampaignMapper,
    private val categoryService: CategoryService,
    private val tagService: TagService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun createCampaign(createDto: CreateCampaignDTO): CampaignDTO {
        val category = categoryService.findByIdOrThrow(createDto.categoryId)
        val tags = tagService.findAllByKeys(createDto.tagIds)

        // * Validate resources count
        this.validateCampaignRequiredResources(tags)

        val campaign = this.saveCampaign(createDto.let {
            CampaignEntity(
                title = it.title,
                description = it.description,
                startAt = it.startAt,
                finishAt = it.finishAt,
                category = category,
                tags = tags.toMutableList()
            )
        })

        logger.info("Campaign created with id ${campaign.id}")

        return campaignMapper.toDto(campaign)
    }

    fun getAllCampaigns(): Page<CampaignDTO> {
        return Page.empty()
    }

    fun getCampaignById() {

    }

    fun findById() {

    }

    fun updateCampaign() {

    }

    fun deleteCampaign() {

    }

    @Transactional
    fun saveCampaign(campaign: CampaignEntity): CampaignEntity = campaignRepository.save(campaign)

    fun validateCampaignRequiredResources(
        tags: List<TagEntity>
    ) {
        if (tags.isEmpty()) throw ApiError.BadRequest("At least one tag is required")
    }
}