package com.example.maktab.module.campaign.dto

import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.example.maktab.module.tag.entity.TagEntity
import java.util.*

data class CampaignDTO(
    val id: String,
    var title: String,
    var description: String,
    var image: StorageResourceEntity,
    var meetStartAt: Date,
    var meetEndAt: Date,
    var meetUrl: String,
    var tags: MutableSet<TagEntity>
)
