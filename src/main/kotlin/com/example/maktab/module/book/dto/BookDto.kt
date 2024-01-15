package com.example.maktab.module.book.dto

import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.Length
import org.springframework.lang.Nullable
import java.awt.print.Book

object BookDTO {
    object Request {
        data class Create(
            @field:NotEmpty
            @field:Length(min = 1, max = 255)
            val title: String,

            @field:Length(max = 512)
            val description: String,

            @field:Min(0)
            val price: Int,
        )

        data class Update(
            @field:NotEmpty
            @field:Length(min = 1, max = 255)
            val title: String,

            @field:Length(max = 512)
            val description: String,

            @Min(0)
            val price: Int,
        )

        data class Search(
            @field:NotEmpty
            @field:Length(max = 255)
            val title: String,
        )
    }

    object Response {
        data class Created(
            val id: String,
            val title: String,
            val description: String,
            val price: Int,
        )

        data class Updated(
            val id: String,
            val title: String,
            val description: String,
            val price: Int,
        )

        data class Removed(
            val id: String,
        )

        data class RetrievedOne(
            val id: String,
            val title: String,
            val description: String,
            val price: Int,
        )

        data class RetrievedAll(
            val items: List<RetrievedOne>,
        )

    }
}