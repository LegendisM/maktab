package com.example.maktab.module.campaign.specification

import com.example.maktab.module.campaign.entity.CampaignEntity
import com.example.maktab.module.campaign.entity.CampaignMemberEntity
import com.example.maktab.module.user.entity.UserEntity
import org.springframework.data.jpa.domain.Specification

object CampaignMemberSpecification {
    fun filter(
        campaignId: String? = null,
        memberId: String? = null,
        userId: String? = null
    ): Specification<CampaignMemberEntity> {
        return Specification.where(
            filterByCampaignId(campaignId)
                .and(filterByMemberId(memberId))
                .and(filterByUserId(userId))
        )
    }

    fun filterByMemberId(memberId: String?) = Specification<CampaignMemberEntity> { root, _, builder ->
        if (memberId == null) return@Specification null

        builder.equal(root.get<String>(CampaignMemberEntity::id.name), memberId)
    }

    fun filterByCampaignId(campaignId: String?) = Specification<CampaignMemberEntity> { root, _, builder ->
        if (campaignId == null) return@Specification null

        val joinCampaign = root.join<CampaignEntity, CampaignMemberEntity>(CampaignMemberEntity::campaign.name)

        builder.equal(joinCampaign.get<String>("id"), campaignId)
    }

    fun filterByUserId(userId: String?) = Specification<CampaignMemberEntity> { root, _, builder ->
        if (userId == null) return@Specification null

        val joinUser = root.join<UserEntity, CampaignMemberEntity>(CampaignMemberEntity::user.name)

        builder.equal(joinUser.get<String>("id"), userId)
    }
}