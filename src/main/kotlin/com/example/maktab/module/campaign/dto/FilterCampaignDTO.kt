package com.example.maktab.module.campaign.dto

import org.hibernate.validator.constraints.Length
import java.util.*

data class FilterCampaignRequestDTO(
    @field:Length(min = 1, max = 255)
    val title: String?,

    var startAt: Date?,

    var finishAt: Date?,

    @field:Length(min = 1, max = 60)
    var category: String?,

    var tags: List<String>?
)
