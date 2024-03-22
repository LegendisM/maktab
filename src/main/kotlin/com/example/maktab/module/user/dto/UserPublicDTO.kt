package com.example.maktab.module.user.dto

import com.example.maktab.module.storage.dto.StorageResourcePublicDTO

data class UserPublicDTO(
    val username: String,
    val avatar: StorageResourcePublicDTO?
)