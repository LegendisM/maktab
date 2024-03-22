package com.example.maktab.module.campaign.specification

import com.example.maktab.module.campaign.dto.FilterCampaignRequestDTO
import com.example.maktab.module.campaign.entity.CampaignEntity
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.tag.entity.TagEntity
import org.springframework.data.jpa.domain.Specification
import java.util.Date

object CampaignSpecification {
    fun filter(filterDto: FilterCampaignRequestDTO): Specification<CampaignEntity> {
        return Specification.where(
            filterByTitle(filterDto.title)
                .and(filterByStartAt(filterDto.startAt))
                .and(filterByFinishAt(filterDto.finishAt))
                .and(filterByCategory(filterDto.category))
                .and(filterByTags(filterDto.tags))
        )
    }

    fun filterByTitle(title: String?) = Specification<CampaignEntity> { root, _, builder ->
        if (title == null) return@Specification null

        builder.like(root.get(CampaignEntity::title.name), "%$title%")
    }

    fun filterByStartAt(startAt: Date?) = Specification<CampaignEntity> { root, _, builder ->
        if (startAt == null) return@Specification null

        builder.greaterThanOrEqualTo(root.get(CampaignEntity::startAt.name), startAt)
    }

    fun filterByFinishAt(finishAt: Date?) = Specification<CampaignEntity> { root, _, builder ->
        if (finishAt == null) return@Specification null

        builder.lessThanOrEqualTo(root.get(CampaignEntity::finishAt.name), finishAt)
    }

    fun filterByCategory(category: String?) = Specification<CampaignEntity> { root, query, builder ->
        if (category == null) return@Specification null

        val joinCategory = root.join<CategoryEntity, CampaignEntity>(CampaignEntity::category.name)

        builder.like(
            builder.lower(
                joinCategory.get(CategoryEntity::title.name)
            ),
            "%${category.lowercase()}%"
        )
    }

    fun filterByTags(tags: List<String>?) = Specification<CampaignEntity> { root, _, builder ->
        if (tags == null) return@Specification null

        val joinTag = root.join<TagEntity, CampaignEntity>(CampaignEntity::tags.name)

        builder.or(
            *tags.map {
                builder.like(
                    builder.lower(
                        joinTag.get(TagEntity::name.name)
                    ),
                    "%$it%"
                )
            }.toTypedArray()
        )
    }
}