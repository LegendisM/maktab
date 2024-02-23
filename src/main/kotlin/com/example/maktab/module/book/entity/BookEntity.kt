package com.example.maktab.module.book.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonIncludeProperties
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "books")
class BookEntity(
    @Column
    var title: String,

    @Column
    var description: String,

    @Column
    var price: Int,

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false)
    @field:JsonIgnoreProperties("url", "key", "id") // TODO: fix property selection
    var image: StorageResourceEntity,

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