package com.example.maktab.module.user.dto

import com.example.maktab.module.storage.entity.StorageResourceEntity

/*
* UserDTO is a definition of basic user properties
* for using as services & controllers responses (for clients)
* */
data class UserDTO(
    val id: String,
    val username: String,
    val email: String?,
    val phone: String?,
    val avatar: StorageResourceEntity?
)