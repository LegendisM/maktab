package com.example.maktab.module.campaign.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.UUID
import java.util.*

data class UpdateCampaignRequestDTO(
    @field:Length(min = 1, max = 255)
    var title: String,

    @field:Length(min = 1, max = 1024)
    var description: String,

    @field:NotNull
    var startAt: Date,

    @field:NotNull
    var finishAt: Date,

    @field:UUID
    var categoryId: String,

    @field:Size(min = 1, max = 3)
    var tagIds: List<String>
)
