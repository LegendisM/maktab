package com.example.maktab.module.user.model

/*
* UserModel is a definition of basic user properties
* for using between services inputs/outputs
* benefit: to have basic structure in transfers between system services
* */
data class UserModel(
    val username: String,
    val email: String,
    val phone: String,
    val password: String
)