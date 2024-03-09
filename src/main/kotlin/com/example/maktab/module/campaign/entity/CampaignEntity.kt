package com.example.maktab.module.campaign.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.example.maktab.module.tag.entity.TagEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "campaigns")
class CampaignEntity(
    title: String,
    description: String,
    image: StorageResourceEntity,
    meetStartAt: Date,
    meetEndAt: Date,
    meetUrl: String,
    tags: MutableSet<TagEntity>
) : BaseEntity() {
    @Column
    var title: String = title

    @Column(length = 512)
    var description: String = description

    @ManyToOne
    @JoinColumn(name = "image_id")
    var image: StorageResourceEntity = image

    @Column
    var meetStartAt: Date = meetStartAt

    @Column
    var meetEndAt: Date = meetEndAt

    @Column
    var meetUrl: String = meetUrl

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "campaign_tags",
        joinColumns = [JoinColumn(name = "campaign_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id", referencedColumnName = "key")]
    )
    var tags: MutableSet<TagEntity> = tags
}