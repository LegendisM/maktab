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
    @Transactional
    fun createPermission(key: String, name: String, groupKey: String): PermissionEntity {
        return savePermission(
            PermissionEntity(
                key = key,
                name = name,
                groupKey = groupKey
            )
        )
    }

    @Transactional(readOnly = true)
    fun findByKey(key: String): Optional<PermissionEntity> {
        return permissionRepository.findById(key)
    }

    @Transactional
    fun deletePermission(key: String) {
        permissionRepository.deleteById(key)
    }

    @Transactional
    fun savePermission(permission: PermissionEntity): PermissionEntity = permissionRepository.save(permission)
}