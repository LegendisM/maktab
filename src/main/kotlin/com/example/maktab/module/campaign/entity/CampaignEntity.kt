package com.example.maktab.module.campaign.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.campaign.dto.CampaignDTO
import com.example.maktab.module.campaign.enums.CampaignStatus
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.tag.entity.TagEntity
import com.example.maktab.module.user.entity.UserEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "campaigns")
class CampaignEntity(
    title: String,
    description: String,
    startAt: Date,
    finishAt: Date,
    currentMemberCount: Int,
    maxMemberCount: Int,
    status: CampaignStatus,
    owner: UserEntity,
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

    @Column
    var currentMemberCount: Int = currentMemberCount

    @Column
    var maxMemberCount: Int = maxMemberCount

    @Column
    @Enumerated(EnumType.STRING)
    var status: CampaignStatus = status

    @ManyToOne
    @JoinColumn(name = "owner_id")
    var owner: UserEntity = owner

    @Column(name = "owner_id", insertable = false, updatable = false)
    val ownerId: String? = null

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: CategoryEntity = category

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "campaign_tags",
        joinColumns = [JoinColumn(name = "campaign_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id", referencedColumnName = "key")]
    )
    var tags: MutableList<TagEntity> = tags
}