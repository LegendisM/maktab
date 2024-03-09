package com.example.maktab.module.tag.entity

import com.example.maktab.common.util.toSlug
import jakarta.persistence.*

@Entity
@Table(name = "tags")
class TagEntity(
    name: String
) {
    @Id
    var key: String = name.toSlug()

    @Column
    var name: String = name

    @PrePersist
    @PreUpdate
    fun normalizeField() {
        key = name.toSlug()
    }
}