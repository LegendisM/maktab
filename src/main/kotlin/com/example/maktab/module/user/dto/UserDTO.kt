package com.example.maktab.module.user.dto

/*
* UserDTO is a definition of basic user properties
* for using as services & controllers responses (for clients)
* */
data class UserDTO(
    val id: String,
    val username: String,
    val email: String,
    val phone: String
)