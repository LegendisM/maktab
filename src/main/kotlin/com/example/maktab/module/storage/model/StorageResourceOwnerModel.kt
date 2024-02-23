package com.example.maktab.module.storage.model

import com.example.maktab.module.storage.enums.StorageResourceOwner

data class StorageResourceOwnerModel(
    val type: StorageResourceOwner,
    val identifier: String
)
