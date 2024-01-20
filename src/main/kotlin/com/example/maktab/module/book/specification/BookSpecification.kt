package com.example.maktab.module.book.specification

import com.example.maktab.common.specification.BaseSpecification
import com.example.maktab.common.util.plus
import com.example.maktab.common.util.times
import com.example.maktab.module.book.dto.FilterBookRequestDto
import com.example.maktab.module.book.entity.BookEntity
import org.springframework.data.jpa.domain.Specification

class BookSpecification(private val filter: FilterBookRequestDto) : BaseSpecification<BookEntity> {
    override fun build() = (filterTitle(filter.title) * filterMinimumPrice() + filterMaximumPrice())

    companion object {
        fun filterTitle(title: String?) = Specification<BookEntity> { root, query, builder ->
            if (title == null) return@Specification null
            builder.like(root.get(BookEntity::title.name), "%${title}%")
        }
    }

    private fun filterMinimumPrice() = Specification<BookEntity> { root, query, builder ->
        if (filter.minimumPrice == null) return@Specification null
        builder.greaterThanOrEqualTo(root.get(BookEntity::price.name), filter.minimumPrice)
    }

    private fun filterMaximumPrice() = Specification<BookEntity> { root, query, builder ->
        if (filter.maximumPrice == null) return@Specification null
        builder.greaterThanOrEqualTo(root.get(BookEntity::price.name), filter.maximumPrice)
    }
}