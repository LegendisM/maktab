package com.example.maktab.module.campaign.mapper

import com.example.maktab.module.campaign.dto.member.CampaignMemberDTO
import com.example.maktab.module.campaign.entity.CampaignMemberEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CampaignMemberMapper {
    fun toDto(entity: CampaignMemberEntity): CampaignMemberDTO
    fun toEntity(dto: CampaignMemberDTO): CampaignMemberEntity
    fun toDtos(entities: List<CampaignMemberEntity>): List<CampaignMemberDTO>
    fun toEntities(dtos: List<CampaignMemberDTO>): List<CampaignMemberEntity>
}