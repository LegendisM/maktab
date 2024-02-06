package com.example.maktab.module.auth.dto

import com.example.maktab.module.auth.enum.AuthTokenType

data class AuthTokenDTO(
    val type: AuthTokenType,
    val value: String
)
