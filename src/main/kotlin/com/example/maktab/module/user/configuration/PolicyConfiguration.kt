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
        val roles: Map<String, Role>,
        val permissions: Map<String, Permission>
    ) {
        data class Role(
            val key: String,
            val name: String,
            val permissions: List<String>,
        )

        data class Permission(
            val key: String,
            val name: String
//            val children: List<Permission>
        )
    }
}