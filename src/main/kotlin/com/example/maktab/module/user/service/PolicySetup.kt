package com.example.maktab.module.user.service

import com.example.maktab.module.user.configuration.PolicyConfiguration
import com.example.maktab.module.user.entity.PermissionEntity
import com.example.maktab.module.user.entity.RoleEntity
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service

@Service
class PolicySetup(
    private val policyConfiguration: PolicyConfiguration,
    private val roleService: RoleService,
    private val permissionService: PermissionService
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        initialize()
    }

    fun initialize() {
        val permissions = policyConfiguration.data.permissions
        val roles = policyConfiguration.data.roles
        val savedPermissions: MutableList<PermissionEntity> = mutableListOf()

        for (permissionGroup in permissions) {
            for (permission in permissionGroup.children) {
                permissionService.deletePermission(permission.key)
                val instance = permissionService.createPermission(
                    PermissionEntity(
                        key = permission.key,
                        name = permission.name,
                        groupKey = permissionGroup.key
                    )
                )
                savedPermissions.add(instance)
            }
        }

        for (role in roles) {
            roleService.deleteRole(role.key)
            roleService.createRole(
                RoleEntity(
                    key = role.key,
                    name = role.name,
                    permissions = role.permissions.mapNotNull { permissionKey ->
                        savedPermissions.find { it.key == permissionKey }
                    }.toMutableList()
                )
            )
        }
    }
}