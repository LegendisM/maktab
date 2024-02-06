package com.example.maktab.common.model

data class PaginationResponseModel<T>(
    val items: List<T>,
    val size: Int,
    val page: Int,
    val total: Int
)
