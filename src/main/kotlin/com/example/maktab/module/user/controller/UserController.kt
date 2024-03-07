package com.example.maktab.module.user.controller

import com.example.maktab.common.annotation.Auth
import com.example.maktab.common.annotation.CurrentUser
import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.user.dto.UpdateUserRequestDTO
import com.example.maktab.module.user.model.UserModel
import com.example.maktab.module.user.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
@Auth
class UserController(
    val userService: UserService
) {
    @GetMapping("/me")
    fun getSelfUser(@CurrentUser user: UserModel): ApiDTO.Response.Success<UserModel> {
        return ApiDTO.Response.Success(user)
    }

    @PatchMapping("/me")
    fun updateSelfUser(
        @RequestBody @Valid updateDto: UpdateUserRequestDTO,
        @CurrentUser user: UserModel
    ) {
        userService.updateUser(user.id, updateDto)
    }
}