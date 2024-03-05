package com.example.maktab.module.user.service

import com.example.maktab.module.user.entity.PermissionEntity
import com.example.maktab.module.user.repository.PermissionRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PermissionService(
    private val permissionRepository: PermissionRepository
) {
    fun createPermission(permission: PermissionEntity): PermissionEntity {
        return permissionRepository.save(permission)
    }

    fun findByKey(key: String): Optional<PermissionEntity> {
        return permissionRepository.findById(key)
    }

    fun deletePermission(key: String) {
        permissionRepository.deleteById(key)
    }
}