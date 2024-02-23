package com.example.maktab.module.storage.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.storage.enums.StorageBucket
import com.example.maktab.module.storage.enums.StorageResourceOwner
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "storage_resources")
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
    @JsonIgnore
    val ownerType: StorageResourceOwner,

    @Column(nullable = false)
    @JsonIgnore
    val ownerId: String,
) : BaseEntity()