package com.example.maktab.module.book.dto

import com.example.maktab.module.category.dto.CategoryDTO
import com.example.maktab.module.storage.entity.StorageResourceEntity
import java.util.Date

data class BookDTO(
    val id: String,
    val title: String,
    val description: String,
    val price: Int,
    val document: StorageResourceEntity,
    val images: List<StorageResourceEntity>,
    val categories: List<CategoryDTO>,
    val createdAt: Date? = null,
    val updatedAt: Date? = null
)