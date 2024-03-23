package com.example.maktab.module.campaign.dto

import com.example.maktab.module.campaign.enums.CampaignStatus
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Length
import java.util.*

data class FilterCampaignRequestDTO(
    @field:Length(min = 1, max = 255)
    val title: String?,

    var startAt: Date?,

    var finishAt: Date?,

    var status: CampaignStatus?,

    @field:Length(min = 1, max = 60)
    var category: String?,

    @field:Size(min = 1)
    var tags: List<String>?
)
