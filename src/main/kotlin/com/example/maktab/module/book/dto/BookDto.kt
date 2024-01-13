package com.example.maktab.module.book.dto

import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.Length
import java.awt.print.Book

object BookDTO {
    object Request {
        data class Create(
            @NotEmpty
            @Length(min = 1, max = 255)
            val title: String,

            @Length(max = 512)
            val description: String,

            @Min(0)
            val price: Int,
        )

        data class Update(
            @NotEmpty
            val id: String,

            @NotEmpty
            @Length(min = 1, max = 255)
            val title: String,

            @Length(max = 512)
            val description: String,

            @Min(0)
            val price: Int,
        )

        data class Remove(
            @NotEmpty
            val id: String,
        )

        data class Search(
            @Length(max = 255)
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