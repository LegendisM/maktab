package com.example.maktab.module.campaign.entity

import com.example.maktab.common.util.toSlug
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table

@Entity
@Table(name = "campaign_tags")
class CampaignTagEntity(
    name: String
) {
    @Id
    var key: String = name.toSlug()

    @Column
    var name: String = name

    @PrePersist
    @PreUpdate
    fun normalizeField() {
        key = name.toSlug()
    }
}