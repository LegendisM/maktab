package com.example.maktab.module.auth.model

import com.example.maktab.module.auth.enums.AuthTokenType

data class AuthToken(
    val type: AuthTokenType,
    val value: String
)
