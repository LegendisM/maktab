package com.example.maktab.module.book.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import jakarta.persistence.*

@Entity
@Table(name = "books")
@JsonDeserialize
class BookEntity(
    title: String,
    description: String,
    price: Int,
    image: StorageResourceEntity,
    categories: MutableSet<CategoryEntity>
) : BaseEntity() {
    @Column
    var title: String = title

    @Column
    var description: String = description

    @Column
    var price: Int = price

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false)
    var image: StorageResourceEntity = image

    @Version
    val version: Long = 0L

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "books_categories",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "category_id", referencedColumnName = "id")]
    )
    var categories: MutableSet<CategoryEntity> = categories
}