package com.example.maktab.module.user.model

import com.example.maktab.module.user.constant.UserConstants
import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.Length

data class CreateUserModel(
    @field:Length(min = UserConstants.MIN_USERNAME_LENGTH, max = UserConstants.MAX_USERNAME_LENGTH)
    val username: String,

    @field:Email
    val email: String,

    @field:Length(min = UserConstants.MIN_PASSWORD_LENGTH, max = UserConstants.MAX_PASSWORD_LENGTH)
    val password: String
)