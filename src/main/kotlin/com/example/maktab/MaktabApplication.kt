package com.example.maktab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class MaktabApplication

fun main(args: Array<String>) {
    runApplication<MaktabApplication>(*args)
}
