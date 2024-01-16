package com.example.maktab.module.book.entity

import jakarta.persistence.*

@Entity
@Table(name = "book")
class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @Column
    var title: String,

    @Column
    var description: String,

    @Column
    var price: Int
)