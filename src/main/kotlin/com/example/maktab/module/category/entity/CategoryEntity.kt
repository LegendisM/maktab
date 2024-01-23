package com.example.maktab.module.category.entity

import jakarta.persistence.*

@Entity
@Table(name = "category")
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @Column(unique = true)
    var title: String,

    @Column()
    var slug: String
)