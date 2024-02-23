package com.example.maktab.module.storage.model

import com.example.maktab.module.storage.enums.StorageBucket

data class CreateStorageResourceModel(
    val key: String,
    val bucket: StorageBucket,
    val url: String?,
    val title: String? = null,
    val description: String? = null,
    val isPrivate: Boolean
)
