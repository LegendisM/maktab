package com.example.maktab.module.campaign.dto.member

import com.example.maktab.module.user.dto.UserPublicDTO

data class CampaignMemberDTO(
    val score: Int,
    val isModerator: Boolean,
    val user: UserPublicDTO
)
