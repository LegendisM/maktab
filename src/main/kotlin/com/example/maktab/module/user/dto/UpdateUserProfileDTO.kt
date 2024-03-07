package com.example.maktab.module.user.dto

import com.example.maktab.module.user.constant.UserConstant
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.UUID

data class UpdateUserProfileRequestDTO(
    @field:Length(min = UserConstant.MIN_USERNAME_LENGTH, max = UserConstant.MAX_USERNAME_LENGTH)
    val username: String?,

    @field:UUID
    val avatarId: String?
)
