package com.example.maktab.module.campaign.dto.member

import org.hibernate.validator.constraints.Range

data class UpdateCampaignMemberRequestDTO(
    var isModerator: Boolean?,

    @field:Range(min = 0, max = 1000)
    var score: Int?
)
