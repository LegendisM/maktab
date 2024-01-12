package com.example.maktab.module.book.entity

import jakarta.persistence.*

@Entity
@Table(name = "book")
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @Column
    val title: String,

    @Column
    val description: String,

    @Column
    val price: Int,
)