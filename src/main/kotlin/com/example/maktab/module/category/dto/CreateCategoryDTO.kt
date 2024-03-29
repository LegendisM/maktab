package com.example.maktab.module.category.dto

import org.hibernate.validator.constraints.Length

data class CreateCategoryRequestDTO(
    @field:Length(min = 1, max = 40)
    val title: String
)
