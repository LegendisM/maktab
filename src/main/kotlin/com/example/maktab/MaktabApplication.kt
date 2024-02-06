package com.example.maktab

import com.example.maktab.common.config.SecretConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@EnableConfigurationProperties(SecretConfiguration::class)
class MaktabApplication

fun main(args: Array<String>) {
    runApplication<MaktabApplication>(*args)
}
