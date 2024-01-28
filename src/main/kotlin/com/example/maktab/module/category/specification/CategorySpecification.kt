package com.example.maktab.module.category.specification

import com.example.maktab.module.category.dto.FilterCategoryRequestDTO
import com.example.maktab.module.category.entity.CategoryEntity
import org.springframework.data.jpa.domain.Specification

object CategorySpecification {
    fun filter(filterDto: FilterCategoryRequestDTO): Specification<CategoryEntity> {
        return Specification.where(
            filterBySlug(filterDto.slug)
        )
    }

    fun filterBySlug(slug: String?) = Specification<CategoryEntity> { root, _, builder ->
        if (slug == null) return@Specification null

        builder.equal(root.get<String>(CategoryEntity::slug.name), slug)
    }
}