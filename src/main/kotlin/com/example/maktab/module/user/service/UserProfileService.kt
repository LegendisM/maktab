package com.example.maktab.module.user.service

import com.example.maktab.module.storage.service.StorageResourceService
import com.example.maktab.module.user.dto.UpdateUserProfileRequestDTO
import com.example.maktab.module.user.dto.UserDTO
import com.example.maktab.module.user.model.UpdateUserModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserProfileService(
    private val userService: UserService,
    private val storageResourceService: StorageResourceService
) {
    @Transactional
    fun updateProfile(id: String, updateDto: UpdateUserProfileRequestDTO): UserDTO {
        val user = userService.findByIdOrThrow(id)
        val updateModel = UpdateUserModel()

        updateModel.apply {
            this.username = updateDto.username

        }

        userService.updateUser(id)
    }
}