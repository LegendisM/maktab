package com.example.maktab.module.campaign.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.user.entity.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "campaign_members")
class CampaignMemberEntity(
    isOwner: Boolean,
    user: UserEntity,
    campaign: CampaignEntity
) : BaseEntity() {
    @Column
    var score: Int = 0

    @Column
    var isOwner: Boolean = isOwner

    @Column
    var isModerator: Boolean = false

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserEntity = user

    @Column(name = "user_id", insertable = false, updatable = false)
    val userId: String? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    var campaign: CampaignEntity = campaign

    @Column(name = "campaign_id", insertable = false, updatable = false)
    val campaignId: String? = null
}