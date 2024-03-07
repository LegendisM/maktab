package com.example.maktab.module.user.service

import com.example.maktab.module.user.entity.PermissionEntity
import com.example.maktab.module.user.repository.PermissionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class PermissionService(
    private val permissionRepository: PermissionRepository
) {
    fun createPermission(permission: PermissionEntity): PermissionEntity {
        return savePermission(permission)
    }

    fun findByKey(key: String): Optional<PermissionEntity> {
        return permissionRepository.findById(key)
    }

    fun deletePermission(key: String) {
        permissionRepository.deleteById(key)
    }

    @Transactional
    fun savePermission(permission: PermissionEntity): PermissionEntity = permissionRepository.save(permission)
}