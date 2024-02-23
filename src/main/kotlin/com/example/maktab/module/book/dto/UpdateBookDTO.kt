package com.example.maktab.module.book.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.UUID

data class UpdateBookRequestDTO(
    @field:Length(min = 1, max = 255)
    val title: String,

    @field:Length(min = 1, max = 512)
    val description: String,

    @field:Min(0)
    val price: Int,

    @field:UUID
    val imageId: String,

    @field:Size(min = 1, max = 10)
    val categories: Set<String>
)