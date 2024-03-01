package com.example.maktab.common.constant

object MultipartFileConstant {
    val VALID_CONTENT_TYPES: List<String> = listOf("image/png", "image/jpg", "image/jpeg", "application/pdf")
    val VALID_CONTENT_TYPES_MESSAGE = MultipartFileConstant.VALID_CONTENT_TYPES.joinToString(", ")
}