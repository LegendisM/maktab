package com.example.maktab.module.book.dto

import com.example.maktab.module.category.dto.CategoryDTO
import com.example.maktab.module.storage.dto.StorageResourcePublicDTO
import java.util.Date

data class BookDTO(
    val id: String,
    val title: String,
    val description: String,
    val price: Int,
    val document: StorageResourcePublicDTO,
    val images: List<StorageResourcePublicDTO>,
    val categories: List<CategoryDTO>,
    val createdAt: Date? = null,
    val updatedAt: Date? = null
)