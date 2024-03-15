package com.example.maktab.module.campaign.service

import com.example.maktab.module.campaign.dto.CampaignDTO
import com.example.maktab.module.campaign.dto.CreateCampaignDTO
import com.example.maktab.module.campaign.entity.CampaignEntity
import com.example.maktab.module.campaign.mapper.CampaignMapper
import com.example.maktab.module.campaign.repository.CampaignRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignService(
    private val campaignRepository: CampaignRepository,
    private val campaignMapper: CampaignMapper
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun createCampaign(createDto: CreateCampaignDTO) {

    }

    fun getAllCampaigns() {

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
}