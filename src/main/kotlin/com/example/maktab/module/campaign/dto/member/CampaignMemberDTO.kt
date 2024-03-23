package com.example.maktab.module.campaign.dto.member

import com.example.maktab.module.campaign.enums.CampaignMemberRole
import com.example.maktab.module.user.dto.UserPublicDTO

data class CampaignMemberDTO(
    var id: String,
    val score: Int,
    val role: CampaignMemberRole,
    val user: UserPublicDTO
)
