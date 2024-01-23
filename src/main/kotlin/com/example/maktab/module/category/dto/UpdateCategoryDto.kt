package com.example.maktab.module.category.dto

import org.hibernate.validator.constraints.Length

data class UpdateCategoryRequestDto(
    @field:Length(min = 1, max = 40)
    val title: String,

    @field:Length(min = 1, max = 60)
    val slug: String
)