package com.example.maktab.module.category.mapper

import com.example.maktab.module.category.dto.CategoryDTO
import com.example.maktab.module.category.dto.CreateCategoryRequestDTO
import com.example.maktab.module.category.entity.CategoryEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface CategoryMapper {
    fun toDto(entity: CategoryEntity): CategoryDTO
    fun toEntity(dto: CategoryDTO): CategoryEntity
    fun toDto(entities: List<CategoryEntity>): List<CategoryDTO>
    fun toEntity(dtos: List<CategoryDTO>): List<CategoryEntity>

    @Mapping(target = "id", constant = "EMPTY")
    @Mapping(target = "slug", constant = "EMPTY")
    fun fromCreateDtoToEntity(create: CreateCategoryRequestDTO): CategoryEntity
}