package com.example.maktab.module.user.model

import com.example.maktab.module.user.constant.UserConstants
import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.Length

data class CreateUserModel(
    val username: String,
    val email: String?,
    val phone: String?,
    val password: String?
)