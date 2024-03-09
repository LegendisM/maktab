package com.example.maktab.module.tag.dto

import org.hibernate.validator.constraints.Length

data class CreateTagRequestDTO(
    @field:Length(min = 1, max = 30)
    val name: String
)
