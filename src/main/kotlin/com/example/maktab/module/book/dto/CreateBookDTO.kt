package com.example.maktab.module.book.dto

import jakarta.validation.constraints.Min
import org.hibernate.validator.constraints.Length

data class CreateBookRequestDTO(
    @field:Length(min = 1, max = 255)
    val title: String,

    @field:Length(min = 1, max = 512)
    val description: String,

    @field:Min(0)
    val price: Int,

    val categories: Set<String>
)