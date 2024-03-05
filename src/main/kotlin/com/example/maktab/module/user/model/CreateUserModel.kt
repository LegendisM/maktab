package com.example.maktab.module.user.model

data class CreateUserModel(
    val username: String,
    val email: String?,
    val phone: String?,
    val password: String?
)