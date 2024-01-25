package com.example.maktab.module.category.mapper

import com.example.maktab.module.category.dto.CategoryDto
import com.example.maktab.module.category.dto.CreateCategoryRequestDto
import com.example.maktab.module.category.entity.CategoryEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface CategoryMapper {
    fun toDto(entity: CategoryEntity): CategoryDto
    fun toEntity(dto: CategoryDto): CategoryEntity
    fun toDto(entities: List<CategoryEntity>): List<CategoryDto>
    fun toEntity(dtos: List<CategoryDto>): List<CategoryEntity>

    @Mapping(target = "id", constant = "EMPTY")
    @Mapping(target = "slug", constant = "EMPTY")
    fun fromCreateDtoToEntity(create: CreateCategoryRequestDto): CategoryEntity
}