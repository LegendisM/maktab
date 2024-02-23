package com.example.maktab.module.book.dto

import com.example.maktab.module.category.dto.CategoryDTO
import com.example.maktab.module.storage.entity.StorageResourceEntity
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length
import java.util.Date

data class BookDTO(
    @field:NotEmpty
    val id: String,

    @field:Length(min = 1, max = 255)
    val title: String,

    @field:Length(min = 1, max = 512)
    val description: String,

    @field:Min(0)
    val price: Int,

    val image: StorageResourceEntity,

    val createdAt: Date? = null,

    val updatedAt: Date? = null,

    val categories: Set<CategoryDTO>
)