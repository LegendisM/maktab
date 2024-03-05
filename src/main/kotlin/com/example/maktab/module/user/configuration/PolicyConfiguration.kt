package com.example.maktab.module.user.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class PolicyConfiguration(
    private val objectMapper: ObjectMapper
) {
    lateinit var data: Data

    init {
        val file = ClassPathResource("policy/policy.json").file
        data = objectMapper.readValue(file, Data::class.java)
    }

    data class Data(
        val defaultUserRoleKey: String,
        val roles: List<Role>,
        val permissions: List<PermissionGroup>
    ) {
        data class Role(
            val key: String,
            val name: String,
            val permissions: List<String>,
        )

        data class PermissionGroup(
            val key: String,
            val name: String,
            val children: List<Permission>
        )

        data class Permission(
            val key: String,
            val name: String
        )
    }
}