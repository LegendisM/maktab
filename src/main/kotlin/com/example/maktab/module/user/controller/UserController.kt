package com.example.maktab.module.user.controller

import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.user.dto.UserDTO
import com.example.maktab.module.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserController(
    val userService: UserService
) {
    @GetMapping("/me")
    fun getSelfUser(): ApiDTO.Response.Success<UserDTO> {
        return ApiDTO.Response.Success(
            UserDTO(
                id = "1-1-1-1",
                username = "Example user",
                email = "makenkap@gmail.com",
                phone = "09016493605"
            )
        )
    }
}