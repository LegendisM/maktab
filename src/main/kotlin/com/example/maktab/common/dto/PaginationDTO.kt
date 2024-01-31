package com.example.maktab.common.dto

data class PaginationResponseDTO<T>(
    val items: List<T>,
    val size: Int,
    val page: Int,
    val total: Int
)