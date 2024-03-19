package com.example.maktab.module.campaign.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.example.maktab.module.tag.entity.TagEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "campaigns")
class CampaignEntity(
    title: String,
    description: String,
    startAt: Date,
    finishAt: Date,
    category: CategoryEntity,
    tags: MutableList<TagEntity>
) : BaseEntity() {
    @Column
    var title: String = title

    @Column(length = 1024)
    var description: String = description

    @Column
    var startAt: Date = startAt

    @Column
    var finishAt: Date = finishAt

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: CategoryEntity = category

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "campaign_tags",
        joinColumns = [JoinColumn(name = "campaign_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id", referencedColumnName = "key")]
    )
    var tags: MutableList<TagEntity> = tags
}