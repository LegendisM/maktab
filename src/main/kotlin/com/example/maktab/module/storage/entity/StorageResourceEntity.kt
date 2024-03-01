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
import jakarta.persistence.Version

@Entity
@Table(name = "storage_resources")
class StorageResourceEntity(
    key: String,
    bucket: StorageBucket,
    contentType: String,
    url: String?,
    title: String?,
    description: String?,
    isPrivate: Boolean,
    ownerType: StorageResourceOwner,
    ownerId: String
) : BaseEntity() {
    @Column
    val key: String = key

    @Column
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    val bucket: StorageBucket = bucket

    @Column
    val contentType: String = contentType

    @Column
    val url: String? = url

    @Column
    val title: String? = title

    @Column
    @JsonIgnore
    val description: String? = description

    @Column
    @JsonIgnore
    val isPrivate: Boolean = isPrivate

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    val ownerType: StorageResourceOwner = ownerType

    @Column(nullable = false)
    @JsonIgnore
    val ownerId: String = ownerId

    @Version
    val version: Long = 0L
}