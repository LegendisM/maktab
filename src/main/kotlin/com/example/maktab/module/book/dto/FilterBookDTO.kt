package com.example.maktab.module.book.dto

import jakarta.validation.constraints.Min
import org.hibernate.validator.constraints.Length

data class FilterBookRequestDTO(
    @field:Length(min = 1, max = 255)
    val title: String?,

    @field:Min(0)
    val minimumPrice: Int?,

    @field:Min(0)
    val maximumPrice: Int?,

    // TODO: category list -> search
)