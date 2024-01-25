package com.example.maktab.module.category.entity

import com.example.maktab.common.util.toSlug
import jakarta.persistence.*

@Entity
@Table(name = "categories")
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @Column(unique = true)
    var title: String,

    @Column()
    var slug: String = ""
) {
    @PrePersist
    @PreUpdate
    fun normalizeField() {
        slug = title.toSlug()
    }
}