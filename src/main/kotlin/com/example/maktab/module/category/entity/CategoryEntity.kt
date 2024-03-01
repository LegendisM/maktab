package com.example.maktab.module.category.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.common.util.toSlug
import jakarta.persistence.*

@Entity
@Table(name = "categories")
class CategoryEntity(
    title: String
) : BaseEntity() {
    @Column(unique = true)
    var title: String = title

    @Column()
    var slug: String = ""

    @Version
    val version: Long = 0L

    @PrePersist
    @PreUpdate
    fun normalizeField() {
        slug = title.toSlug()
    }
}