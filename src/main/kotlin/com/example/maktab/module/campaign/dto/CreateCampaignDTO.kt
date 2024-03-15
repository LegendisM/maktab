package com.example.maktab.module.campaign.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import org.hibernate.validator.constraints.UUID
import java.util.*

data class CreateCampaignDTO(
    @field:Length(min = 1, max = 255)
    var title: String,

    @field:Length(min = 1, max = 512)
    var description: String,

    @field:UUID
    var imageId: String,

    @field:NotNull
    var meetStartAt: Date,

    @field:NotNull
    var meetEndAt: Date,

    @field:URL
    var meetUrl: String,

    @field:Size(min = 1, max = 3)
    var tagIds: List<String>
)
