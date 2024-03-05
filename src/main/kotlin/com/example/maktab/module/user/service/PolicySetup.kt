package com.example.maktab.module.user.service

import com.example.maktab.module.user.configuration.PolicyConfiguration
import com.example.maktab.module.user.repository.PermissionRepository
import com.example.maktab.module.user.repository.RoleRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service

@Service
class PolicySetup(
    private val policyConfiguration: PolicyConfiguration,
    private val roleRepository: RoleRepository,
    private val permissionRepository: PermissionRepository,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        initialize()
    }

    fun initialize() {
        print(policyConfiguration.data.roles.map { it.value.name }.joinToString(", "))
        print(policyConfiguration.data.permissions.map { it.value.name }.joinToString(", "))
        // TODO: read file from resources and load one by one
    }
}