package com.example.maktab.common.annotation

import com.example.maktab.common.validator.MultipartFileValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [MultipartFileValidator::class])
annotation class ValidMultipartFile(
    val message: String = "Invalid multipart-file",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
