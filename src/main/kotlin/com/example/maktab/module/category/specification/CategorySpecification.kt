package com.example.maktab.module.category.specification

import com.example.maktab.common.specification.BaseSpecification
import com.example.maktab.module.category.dto.FilterCategoryRequestDto
import com.example.maktab.module.category.entity.CategoryEntity
import org.springframework.data.jpa.domain.Specification

class CategorySpecification(private val filter: FilterCategoryRequestDto) : BaseSpecification<CategoryEntity> {
    override fun build() = (filterBySlug(filter.slug))

    companion object {
        fun filterBySlug(slug: String?) = Specification<CategoryEntity> { root, _, builder ->
            if (slug == null) return@Specification null
            builder.equal(root.get<String>(CategoryEntity::slug.name), slug)
        }
    }
}