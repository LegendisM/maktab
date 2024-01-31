package com.example.maktab.module.category.specification

import com.example.maktab.module.category.dto.FilterCategoryRequestDTO
import com.example.maktab.module.category.entity.CategoryEntity
import org.springframework.data.jpa.domain.Specification

object CategorySpecification {
    fun filter(filterDto: FilterCategoryRequestDTO): Specification<CategoryEntity> {
        return Specification.where(
            filterByTitle(filterDto.title)
                .and(filterBySlug(filterDto.slug))
        )
    }

    fun filterByTitle(title: String?) = Specification<CategoryEntity> { root, _, builder ->
        if (title == null) return@Specification null

        builder.like(
            builder.lower(root.get<String>(CategoryEntity::title.name)),
            "%${title.lowercase()}%"
        )
    }

    fun filterBySlug(slug: String?) = Specification<CategoryEntity> { root, _, builder ->
        if (slug == null) return@Specification null

        builder.like(
            builder.lower(root.get<String>(CategoryEntity::slug.name)),
            "%${slug.lowercase()}%"
        )
    }
}