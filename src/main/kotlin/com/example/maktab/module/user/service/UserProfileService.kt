package com.example.maktab.module.user.service

import com.example.maktab.module.storage.service.StorageResourceService
import com.example.maktab.module.user.dto.UpdateUserProfileRequestDTO
import com.example.maktab.module.user.dto.UserDTO
import com.example.maktab.module.user.mapper.UserMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserProfileService(
    private val userService: UserService,
    private val userMapper: UserMapper,
    private val storageResourceService: StorageResourceService,
) {
    @Transactional
    fun updateProfile(id: String, updateDto: UpdateUserProfileRequestDTO): UserDTO {
        val user = userService.findByIdOrThrow(id)

        user.apply {
            updateDto.username?.let {
                this.username = it
            }

            updateDto.avatarId?.let {
                this.avatar = storageResourceService.findByIdOrThrow(it)
            }
        }

        userService.saveUser(user)

        return userMapper.toDto(user)
    }
}