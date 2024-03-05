package com.example.maktab.module.user.model

import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.example.maktab.module.user.entity.RoleEntity

/*
* UserModel is a definition of basic user properties
* for using between services inputs/outputs
* benefit: to have basic structure in transfers between system services
* */
data class UserModel(
    val id: String,
    val username: String,
    val email: String?,
    val phone: String?,
    val role: RoleEntity,
    val avatar: StorageResourceEntity?
)