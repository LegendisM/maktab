package com.example.maktab.module.storage.model

import com.example.maktab.module.storage.enums.StorageBucket

data class StorageResourceModel(
    val id: String,
    val key: String,
    val bucket: StorageBucket,
    val url: String?,
    val title: String?,
    val description: String?,
    val createdAt: String?,
    val updatedAt: String?
)
