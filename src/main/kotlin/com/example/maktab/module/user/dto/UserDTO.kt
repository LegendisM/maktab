package com.example.maktab.module.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

/*
* UserDTO is a definition of basic user properties
* for using as services & controllers responses (for clients)
* */
data class UserDTO(
    @field:NotEmpty
    val id: String,

    @field:NotEmpty
    val username: String,

    @field:Email
    val email: String,

    @field:NotEmpty
    val phone: String
)