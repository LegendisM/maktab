package com.example.maktab.module.user.model

import com.example.maktab.module.user.constant.UserConstant
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length

data class FilterUserModel(
    @field:Length(min = UserConstant.MIN_USERNAME_LENGTH, max = UserConstant.MAX_USERNAME_LENGTH)
    val username: String?,

    @field:Email
    val email: String?,

    @field:NotEmpty
    val phone: String?
)
