package com.example.maktab.module.campaign.mapper

import com.example.maktab.module.campaign.dto.CampaignDTO
import com.example.maktab.module.campaign.entity.CampaignEntity
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CampaignMapper {
    fun toDto(entity: CampaignEntity): CampaignDTO
    fun toEntity(dto: CampaignDTO): CampaignEntity
    fun toDtos(entities: List<CampaignEntity>): List<CampaignDTO>
    fun toEntities(dtos: List<CampaignDTO>): List<CampaignEntity>
}