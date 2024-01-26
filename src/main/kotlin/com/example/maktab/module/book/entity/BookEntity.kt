package com.example.maktab.module.book.entity

import com.example.maktab.module.category.entity.CategoryEntity
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@Entity
@Table(name = "books")
class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @Column
    var title: String,

    @Column
    var description: String,

    @Column
    var price: Int,

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    var createdAt: Instant = Instant.now(),

    @Column(name = "updated_at")
    @LastModifiedDate
    var updatedAt: Instant = Instant.now(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "books_categories",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "category_id", referencedColumnName = "id")]
    )
    var categories: MutableSet<CategoryEntity> = mutableSetOf()
)