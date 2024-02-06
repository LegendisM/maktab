package com.example.maktab.module.book.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.category.entity.CategoryEntity
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.sql.Date
import java.sql.Timestamp
import java.time.Instant

@Entity
@Table(name = "books")
class BookEntity(
    @Column
    var title: String,

    @Column
    var description: String,

    @Column
    var price: Int,

    // TODO: relate to Resource entity
    @Column
    val file: String,

    @Version
    val version: Long = 0L,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "books_categories",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "category_id", referencedColumnName = "id")]
    )
    var categories: MutableSet<CategoryEntity> = mutableSetOf()
) : BaseEntity()