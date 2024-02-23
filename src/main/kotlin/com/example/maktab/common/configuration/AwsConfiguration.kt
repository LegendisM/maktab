package com.example.maktab.common.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.cloud.aws")
data class AwsConfiguration(
    val s3: AwsS3Configuration,
    val credentials: AwsCredentialsConfiguration
)

data class AwsS3Configuration(
    val enabled: Boolean,
    val endpoint: String,
    val region: String,
)

data class AwsCredentialsConfiguration(
    val accessKey: String,
    val secretKey: String
)