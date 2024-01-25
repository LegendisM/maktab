package com.example.maktab.module.category.dto

import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length

data class CategoryDTO(
    @field:NotEmpty
    val id: String,

    @field:Length(min = 1, max = 60)
    val title: String,

    @field:Length(min = 1)
    val slug: String
)
