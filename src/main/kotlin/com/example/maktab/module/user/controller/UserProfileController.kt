package com.example.maktab.module.user.controller

import com.example.maktab.common.annotation.Auth
import com.example.maktab.common.annotation.CurrentUser
import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.user.dto.UpdateUserProfileRequestDTO
import com.example.maktab.module.user.dto.UserDTO
import com.example.maktab.module.user.model.UserModel
import com.example.maktab.module.user.service.UserProfileService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/profiles")
@Auth
class UserProfileController(
    private val userProfileService: UserProfileService
) {
    @PatchMapping("/me")
    fun updateSelfUserProfile(
        @RequestBody @Valid updateDto: UpdateUserProfileRequestDTO,
        @CurrentUser user: UserModel
    ): ApiDTO.Response.Success<UserDTO> {
        val result = userProfileService.updateProfile(user.id, updateDto)

        return ApiDTO.Response.Success(result)
    }
}