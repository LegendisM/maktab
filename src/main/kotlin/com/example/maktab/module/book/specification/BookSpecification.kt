package com.example.maktab.module.book.specification

import com.example.maktab.module.book.dto.FilterBookRequestDTO
import com.example.maktab.module.book.entity.BookEntity
import com.example.maktab.module.category.entity.CategoryEntity
import org.springframework.data.jpa.domain.Specification

object BookSpecification {
    fun filter(filterDto: FilterBookRequestDTO): Specification<BookEntity> {
        return Specification.where(
            filterByTitle(filterDto.title)
                .and(filterByMaximumPrice(filterDto.maximumPrice))
                .and(filterByMinimumPrice(filterDto.minimumPrice))
                .and(filterByCategory(filterDto.category))
        )
    }

    fun filterByTitle(title: String?) = Specification<BookEntity> { root, _, builder ->
        if (title == null) return@Specification null

        builder.like(root.get(BookEntity::title.name), "%$title%")
    }

    fun filterByMinimumPrice(minimumPrice: Int?) = Specification<BookEntity> { root, _, builder ->
        if (minimumPrice == null) return@Specification null

        builder.greaterThanOrEqualTo(root.get(BookEntity::price.name), minimumPrice)
    }

    fun filterByMaximumPrice(maximumPrice: Int?) = Specification<BookEntity> { root, _, builder ->
        if (maximumPrice == null) return@Specification null

        builder.lessThanOrEqualTo(root.get(BookEntity::price.name), maximumPrice)
    }

    fun filterByCategory(category: String?) = Specification<BookEntity> { root, _, builder ->
        if (category == null) return@Specification null

        val bookCategories = root.join<CategoryEntity, BookEntity>(BookEntity::categories.name)

        builder.like(
            builder.lower(
                bookCategories.get(CategoryEntity::title.name)
            ),
            "%${category.lowercase()}%"
        )
    }
}