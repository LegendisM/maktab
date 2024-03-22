package com.example.maktab.module.campaign.dto

import com.example.maktab.module.category.dto.CategoryDTO
import com.example.maktab.module.tag.dto.TagDTO
import com.example.maktab.module.user.dto.UserPublicDTO
import java.util.*

data class CampaignDTO(
    val id: String,
    var title: String,
    var description: String,
    var startAt: Date,
    var finishAt: Date,
    var owner: UserPublicDTO,
    var category: CategoryDTO,
    var tags: MutableList<TagDTO>
)
