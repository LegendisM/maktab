package com.example.maktab.module.storage.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.storage.enums.StorageBucket
import com.example.maktab.module.storage.enums.StorageResourceOwner
import com.fasterxml.jackson.annotation.JsonFilter
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "storage_resources")
@JsonFilter("STORAGE_RESOURCE_FILTER")
class StorageResourceEntity(
    @Column
    val key: String,

    @Column
    @Enumerated(EnumType.STRING)
    val bucket: StorageBucket,

    @Column
    val url: String?,

    @Column
    val title: String?,

    @Column
    val description: String?,

    @Column
    val isPrivate: Boolean,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val ownerType: StorageResourceOwner,

    @Column(nullable = false)
    val ownerId: String,
) : BaseEntity()