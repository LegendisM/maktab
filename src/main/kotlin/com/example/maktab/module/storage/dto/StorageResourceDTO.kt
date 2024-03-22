package com.example.maktab.module.storage.dto

import com.example.maktab.module.storage.enums.StorageBucket
import com.example.maktab.module.storage.enums.StorageResourceOwner

data class StorageResourceDTO(
    var key: String,
    var bucket: StorageBucket,
    var contentType: String,
    var url: String?,
    var title: String?,
    var description: String?,
    var isPrivate: Boolean,
    var ownerType: StorageResourceOwner,
    var ownerId: String
)
