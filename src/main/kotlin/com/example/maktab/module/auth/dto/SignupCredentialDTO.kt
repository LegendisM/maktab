package com.example.maktab.module.auth.dto

import com.example.maktab.module.user.constant.UserConstant
import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.Length

data class SignupCredentialRequestDTO(
    @field:Length(min = UserConstant.MIN_USERNAME_LENGTH, max = UserConstant.MAX_USERNAME_LENGTH)
    val username: String,

    @field:Email
    val email: String,

    @field:Length(min = UserConstant.MIN_PASSWORD_LENGTH, max = UserConstant.MAX_PASSWORD_LENGTH)
    val password: String
)