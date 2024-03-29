package com.example.maktab.module.book.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.storage.entity.StorageResourceEntity
import jakarta.persistence.*

@Entity
@Table(name = "books")
class BookEntity(
    title: String,
    description: String,
    price: Int,
    document: StorageResourceEntity,
    images: MutableList<StorageResourceEntity>,
    categories: MutableList<CategoryEntity>,
) : BaseEntity() {
    @Column
    var title: String = title

    @Column
    var description: String = description

    @Column
    var price: Int = price

    @OneToOne
    @JoinColumn(name = "document_id")
    var document: StorageResourceEntity = document

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "book_images",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "image_id", referencedColumnName = "id")]
    )
    var images: MutableList<StorageResourceEntity> = images

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "book_categories",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "category_id", referencedColumnName = "id")]
    )
    var categories: MutableList<CategoryEntity> = categories

    @Version
    val version: Long = 0L
}