package com.example.maktab.module.campaign.dto

import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.tag.entity.TagEntity
import java.util.*

data class CampaignDTO(
    val id: String,
    var title: String,
    var description: String,
    var startAt: Date,
    var finishAt: Date,
    var category: CategoryEntity,
    var tags: MutableList<TagEntity>
)
