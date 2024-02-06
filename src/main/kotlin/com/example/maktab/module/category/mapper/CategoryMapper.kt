package com.example.maktab.module.category.mapper

import com.example.maktab.module.category.dto.CategoryDTO
import com.example.maktab.module.category.dto.CreateCategoryRequestDTO
import com.example.maktab.module.category.entity.CategoryEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CategoryMapper {
    fun toDto(entity: CategoryEntity): CategoryDTO
    fun toEntity(dto: CategoryDTO): CategoryEntity
    fun toDtos(entities: List<CategoryEntity>): List<CategoryDTO>
    fun toEntities(dtos: List<CategoryDTO>): List<CategoryEntity>

    @Mapping(target = "slug", constant = "EMPTY")
    fun fromCreateDtoToEntity(createDto: CreateCategoryRequestDTO): CategoryEntity
}