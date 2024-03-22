package com.example.maktab.module.user.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.user.configuration.PolicyConfiguration
import com.example.maktab.module.user.entity.PermissionEntity
import com.example.maktab.module.user.entity.RoleEntity
import com.example.maktab.module.user.repository.RoleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class RoleService(
    private val roleRepository: RoleRepository,
    private val policyConfiguration: PolicyConfiguration
) {
    @Transactional
    fun createRole(key: String, name: String, permissions: MutableList<PermissionEntity>): RoleEntity {
        return saveRole(
            RoleEntity(
                key = key,
                name = name,
                permissions = permissions
            )
        )
    }

    @Transactional(readOnly = true)
    fun findByKey(key: String): Optional<RoleEntity> {
        return roleRepository.findById(key)
    }

    @Transactional(readOnly = true)
    fun getDefaultUserRole(): RoleEntity {
        return findByKey(policyConfiguration.data.defaultUserRoleKey).orElseThrow {
            ApiError.Conflict("Default user role not found")
        }
    }

    @Transactional
    fun deleteRole(key: String) {
        roleRepository.deleteById(key)
    }

    @Transactional
    fun saveRole(role: RoleEntity): RoleEntity = roleRepository.save(role)
}