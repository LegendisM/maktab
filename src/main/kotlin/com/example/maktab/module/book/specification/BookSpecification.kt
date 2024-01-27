package com.example.maktab.module.book.specification

import com.example.maktab.common.specification.BaseSpecification
import com.example.maktab.common.util.plus
import com.example.maktab.common.util.times
import com.example.maktab.module.book.dto.FilterBookRequestDTO
import com.example.maktab.module.book.entity.BookEntity
import com.example.maktab.module.category.entity.CategoryEntity
import org.springframework.data.jpa.domain.Specification

class BookSpecification(private val filter: FilterBookRequestDTO) : BaseSpecification<BookEntity> {
    override fun build() =
        (filterTitle(filter.title) + filterCategories(filter.categories) + filterMinimumPrice() + filterMaximumPrice())

    companion object {
        fun filterTitle(title: String?) = Specification<BookEntity> { root, _, builder ->
            if (title == null) return@Specification null
            builder.like(root.get(BookEntity::title.name), "%${title}%")
        }

        fun filterCategories(categories: Set<String>?) = Specification<BookEntity> { root, _, builder ->
            if (categories == null) return@Specification null
            builder.`in`(root.get<Set<CategoryEntity>>("categories"))
            return@Specification null // TODO: impl the logic of find books by categories ids
        }
    }

    private fun filterMinimumPrice() = Specification<BookEntity> { root, _, builder ->
        if (filter.minimumPrice == null) return@Specification null
        builder.greaterThanOrEqualTo(root.get(BookEntity::price.name), filter.minimumPrice)
    }

    private fun filterMaximumPrice() = Specification<BookEntity> { root, query, builder ->
        if (filter.maximumPrice == null) return@Specification null
        builder.greaterThanOrEqualTo(root.get(BookEntity::price.name), filter.maximumPrice)
    }
}