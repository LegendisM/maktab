package com.example.maktab.common.annotation

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Policy(
    val permissions: Array<String> = [],
    val roles: Array<String> = []
)