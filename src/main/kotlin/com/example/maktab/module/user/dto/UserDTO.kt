package com.example.maktab.module.user.dto

import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.example.maktab.module.user.entity.RoleEntity

/*
* UserDTO is a definition of basic user properties
* for using as services & controllers responses (for clients)
* */
data class UserDTO(
    val id: String,
    val username: String,
    val email: String?,
    val phone: String?,
    val role: RoleEntity,
    val avatar: StorageResourceEntity?
)