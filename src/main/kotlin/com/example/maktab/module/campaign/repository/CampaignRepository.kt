package com.example.maktab.module.campaign.repository

import com.example.maktab.module.campaign.entity.CampaignEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CampaignRepository : JpaRepository<CampaignEntity, String>, JpaSpecificationExecutor<CampaignEntity>