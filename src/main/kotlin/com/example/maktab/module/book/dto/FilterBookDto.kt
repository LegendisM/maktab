package com.example.maktab.module.book.dto

import jakarta.validation.constraints.Min
import org.hibernate.validator.constraints.Length
import org.springframework.lang.Nullable

data class FilterBookRequestDto(
    @field:Length(min = 1, max = 255)
    val title: String?,

    @field:Min(0)
    val minimumPrice: Int?,

    @field:Min(0)
    val maximumPrice: Int?
)