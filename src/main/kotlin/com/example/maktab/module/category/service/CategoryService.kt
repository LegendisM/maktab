package com.example.maktab.module.category.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.category.dto.CategoryDto
import com.example.maktab.module.category.dto.CreateCategoryRequestDto
import com.example.maktab.module.category.dto.UpdateCategoryRequestDto
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.category.mapper.CategoryMapper
import com.example.maktab.module.category.repository.CategoryRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    val categoryRepository: CategoryRepository,
    val categoryMapper: CategoryMapper
) {
    private val logger = LoggerFactory.getLogger(CategoryService::class.simpleName)

    @Transactional
    fun createCategory(createDto: CreateCategoryRequestDto): CategoryDto {
        val category = categoryRepository.save(
            categoryMapper.fromCreateDtoToEntity(createDto)
        )

        logger.info("Category created with id ${category.id}")

        return categoryMapper.toDto(category)
    }

    @Transactional(readOnly = true)
    fun getAllCategories(): List<CategoryDto> {
        val categories = categoryRepository.findAll()

        return categoryMapper.toDto(categories)
    }

    fun getCategoryById(id: String): CategoryDto {
        val category = findByIdOrThrow(id)

        return categoryMapper.toDto(category)
    }

    fun findByIdOrThrow(id: String): CategoryEntity {
        return categoryRepository.findById(id).orElseThrow { ApiError.NotFound("Invalid Id") }
    }

    @Transactional
    fun updateCategory(id: String, updateDto: UpdateCategoryRequestDto): CategoryDto {
        val category = findByIdOrThrow(id)

        category.apply {
            this.title = updateDto.title
            this.slug = updateDto.slug
        }

        categoryRepository.save(category)

        logger.info("The category $id updated successfully")

        return categoryMapper.toDto(category)
    }

    fun deleteCategory(id: String) {
        val category = findByIdOrThrow(id)

        categoryRepository.delete(category)

        logger.info("The category $id removed successfully")
    }
}