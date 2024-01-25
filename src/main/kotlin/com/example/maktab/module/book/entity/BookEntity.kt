package com.example.maktab.module.book.entity

import com.example.maktab.module.category.entity.CategoryEntity
import jakarta.persistence.*

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "books_categories",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "category_id", referencedColumnName = "id")]
    )
    var categories: MutableSet<CategoryEntity> = hashSetOf()
)