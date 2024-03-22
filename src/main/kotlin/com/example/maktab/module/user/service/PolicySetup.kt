package com.example.maktab.module.user.service

import com.example.maktab.module.user.configuration.PolicyConfiguration
import com.example.maktab.module.user.entity.PermissionEntity
import com.example.maktab.module.user.entity.RoleEntity
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class PolicySetup(
    private val policyConfiguration: PolicyConfiguration,
    private val roleService: RoleService,
    private val permissionService: PermissionService
) : ApplicationRunner {
    @Transactional
    override fun run(args: ApplicationArguments?) {
        initialize()
    }

    @Transactional
    fun initialize() {
        val permissions = policyConfiguration.data.permissions
        val roles = policyConfiguration.data.roles
        val savedPermissions: MutableList<PermissionEntity> = mutableListOf()

        for (permissionGroup in permissions) {
            for (permissionData in permissionGroup.children) {
                var permission = permissionService.findByKey(permissionData.key).getOrNull()

                if (permission == null) {
                    permission = permissionService.createPermission(
                        key = permissionData.key,
                        name = permissionData.name,
                        groupKey = permissionGroup.key
                    )
                } else {
                    permission.apply {
                        this.name = permissionData.name
                        this.groupKey = permissionGroup.key
                    }
                    permissionService.savePermission(permission)
                }

                savedPermissions.add(permission)
            }
        }

        for (roleData in roles) {
            val role = roleService.findByKey(roleData.key).getOrNull()
            val rolePermissions = roleData.permissions.mapNotNull { permissionKey ->
                savedPermissions.find { it.key == permissionKey }
            }.toMutableList()

            if (role == null) {
                roleService.createRole(
                    key = roleData.key,
                    name = roleData.name,
                    permissions = rolePermissions
                )
            } else {
                role.apply {
                    this.name = roleData.name
                    this.permissions = rolePermissions
                }
                roleService.saveRole(role)
            }
        }
    }
}