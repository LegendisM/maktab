package com.example.maktab.module.user.service

import com.example.maktab.module.user.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class UserService(
    val userMapper: UserMapper,
)