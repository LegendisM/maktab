package com.example.maktab.module.auth.model

data class ValidateAccountModel(
    var username: String?,
    var email: String? = null,
    var phone: String? = null,
    var password: String? = null
)