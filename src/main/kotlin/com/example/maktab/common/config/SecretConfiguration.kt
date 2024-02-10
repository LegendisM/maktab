package com.example.maktab.common.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "secret")
data class SecretConfiguration(
    val jwt: SecretJwtConfiguration
)

data class SecretJwtConfiguration(
    val accessToken: SecretJwtPropertiesConfiguration,
    val refreshToken: SecretJwtPropertiesConfiguration
)

data class SecretJwtPropertiesConfiguration(
    val key: String,
    val expire: Long
)
