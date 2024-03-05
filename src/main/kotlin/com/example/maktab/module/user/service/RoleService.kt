package com.example.maktab.module.user.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.user.configuration.PolicyConfiguration
import com.example.maktab.module.user.entity.RoleEntity
import com.example.maktab.module.user.repository.RoleRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class RoleService(
    private val roleRepository: RoleRepository,
    private val policyConfiguration: PolicyConfiguration
) {
    fun createRole(role: RoleEntity): RoleEntity {
        return roleRepository.save(role)
    }

    fun findByKey(key: String): Optional<RoleEntity> {
        return roleRepository.findById(key)
    }

    fun getDefaultUserRole(): RoleEntity {
        return findByKey(policyConfiguration.data.defaultUserRoleKey).orElseThrow {
            ApiError.Conflict("Default user role not found")
        }
    }

    fun deleteRole(key: String) {
        roleRepository.deleteById(key)
    }
}