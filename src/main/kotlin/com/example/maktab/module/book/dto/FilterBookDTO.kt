package com.example.maktab.module.book.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Length

data class FilterBookRequestDTO(
    @field:Length(min = 1, max = 255)
    val title: String?,

    @field:Min(0)
    val minimumPrice: Int?,

    @field:Min(0)
    val maximumPrice: Int?,

    @field:Length(min = 1, max = 60)
    val category: String? // TODO: need a fix patch
)