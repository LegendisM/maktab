package com.example.maktab.module.category.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.common.util.toSlug
import jakarta.persistence.*

@Entity
@Table(name = "categories")
class CategoryEntity(
    @Column(unique = true)
    var title: String,

    @Column()
    var slug: String = "",

    @Version
    val version: Long = 0L
) : BaseEntity() {
    @PrePersist
    @PreUpdate
    fun normalizeField() {
        slug = title.toSlug()
    }
}