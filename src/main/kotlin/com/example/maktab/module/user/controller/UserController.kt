package com.example.maktab.module.user.controller

import com.example.maktab.module.user.service.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserController(
    val userService: UserService
)