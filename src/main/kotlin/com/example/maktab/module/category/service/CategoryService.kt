package com.example.maktab.module.category.service

import com.example.maktab.common.dto.PaginationResponseDTO
import com.example.maktab.common.exception.ApiError
import com.example.maktab.common.util.toSlug
import com.example.maktab.module.category.dto.CategoryDTO
import com.example.maktab.module.category.dto.CreateCategoryRequestDTO
import com.example.maktab.module.category.dto.FilterCategoryRequestDTO
import com.example.maktab.module.category.dto.UpdateCategoryRequestDTO
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.category.mapper.CategoryMapper
import com.example.maktab.module.category.repository.CategoryRepository
import com.example.maktab.module.category.specification.CategorySpecification
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val categoryMapper: CategoryMapper
) {
    private val logger = LoggerFactory.getLogger(CategoryService::class.simpleName)

    @Transactional
    fun createCategory(createDto: CreateCategoryRequestDTO): CategoryDTO {
        val exists = categoryRepository.exists(
            CategorySpecification.filterBySlug(
                createDto.title.toSlug()
            )
        )

        if (exists) throw ApiError.Custom("Category already exists.", status = HttpStatus.CONFLICT)

        val category = categoryRepository.save(
            categoryMapper.fromCreateDtoToEntity(createDto)
        )

        logger.info("Category created with id ${category.id}")

        return categoryMapper.toDto(category)
    }

    @Transactional(readOnly = true)
    fun getAllCategories(filterDto: FilterCategoryRequestDTO, page: Pageable): PaginationResponseDTO<CategoryDTO> {
        val categories = categoryRepository.findAll(CategorySpecification.filter(filterDto), page)

        return PaginationResponseDTO(
            items = categoryMapper.toDtos(categories.toList()),
            size = categories.size,
            page = page.pageNumber,
            total = categories.totalPages
        )
    }

    @Transactional(readOnly = true)
    fun findAllByIds(ids: Set<String>): List<CategoryEntity> {
        return categoryRepository.findAllById(ids)
    }

    @Transactional(readOnly = true)
    fun getCategoryById(id: String): CategoryDTO {
        val category = this.findByIdOrThrow(id)

        return categoryMapper.toDto(category)
    }

    @Transactional(readOnly = true)
    fun findByIdOrThrow(id: String): CategoryEntity {
        return categoryRepository.findById(id).orElseThrow { ApiError.NotFound("Invalid Id") }
    }

    @Transactional
    fun updateCategory(id: String, updateDto: UpdateCategoryRequestDTO): CategoryDTO {
        val category = this.findByIdOrThrow(id)

        category.apply {
            this.title = updateDto.title
        }

        categoryRepository.save(category)

        logger.info("The category $id updated successfully")

        return categoryMapper.toDto(category)
    }

    @Transactional
    fun deleteCategory(id: String) {
        val category = this.findByIdOrThrow(id)

        categoryRepository.delete(category)

        logger.info("The category $id removed successfully")
    }
}