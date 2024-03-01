package com.example.maktab.module.category.dto

import java.util.Date

data class CategoryDTO(
    val id: String,
    val title: String,
    val slug: String,
    val createdAt: Date? = null,
    val updatedAt: Date? = null
)
