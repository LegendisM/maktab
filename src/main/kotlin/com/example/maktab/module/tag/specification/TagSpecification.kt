package com.example.maktab.module.tag.specification

import com.example.maktab.module.tag.dto.FilterTagRequestDTO
import com.example.maktab.module.tag.entity.TagEntity
import org.springframework.data.jpa.domain.Specification

object TagSpecification {
    fun filter(filterDto: FilterTagRequestDTO): Specification<TagEntity> {
        return Specification.where(
            filterByName(filterDto.name)
        )
    }

    fun filterByName(name: String?) = Specification<TagEntity> { root, _, builder ->
        if (name == null) return@Specification null

        builder.like(
            builder.lower(root.get<String>(TagEntity::name.name)),
            "%${name.lowercase()}%"
        )
    }
}