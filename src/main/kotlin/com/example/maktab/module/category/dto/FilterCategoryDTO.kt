package com.example.maktab.module.category.dto

import org.hibernate.validator.constraints.Length

data class FilterCategoryRequestDTO(
    @field:Length(min = 1, max = 60)
    val title: String,

    @field:Length(min = 1)
    val slug: String
)