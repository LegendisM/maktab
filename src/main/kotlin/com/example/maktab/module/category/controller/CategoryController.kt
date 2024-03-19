package com.example.maktab.module.category.controller

import com.example.maktab.common.annotation.Auth
import com.example.maktab.common.annotation.Policy
import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.category.dto.CategoryDTO
import com.example.maktab.module.category.dto.CreateCategoryRequestDTO
import com.example.maktab.module.category.dto.FilterCategoryRequestDTO
import com.example.maktab.module.category.dto.UpdateCategoryRequestDTO
import com.example.maktab.module.category.service.CategoryService
import jakarta.validation.Valid
import org.hibernate.validator.constraints.UUID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @PostMapping
    @Auth
    @Policy(permissions = ["category-management"])
    fun createCategory(
        @RequestBody @Valid createDto: CreateCategoryRequestDTO
    ): ApiDTO.Response.Success<CategoryDTO> {
        val result = categoryService.createCategory(createDto)

        return ApiDTO.Response.Success(result, status = HttpStatus.CREATED)
    }

    @PostMapping("/filter")
    fun getAllCategories(
        @RequestBody @Valid filterDto: FilterCategoryRequestDTO,
        page: Pageable
    ): ApiDTO.Response.Success<Page<CategoryDTO>> {
        val result = categoryService.getAllCategories(filterDto, page)

        return ApiDTO.Response.Success(result)
    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable("id") id: String): ApiDTO.Response.Success<CategoryDTO> {
        val category = categoryService.getCategoryById(id)

        return ApiDTO.Response.Success(category)
    }

    @PatchMapping("/{id}")
    @Auth
    @Policy(permissions = ["category-management"])
    fun updateCategory(
        @PathVariable("id") @Valid @UUID id: String,
        @RequestBody @Valid updateDto: UpdateCategoryRequestDTO
    ): ApiDTO.Response.Success<CategoryDTO> {
        val result = categoryService.updateCategory(id, updateDto)

        return ApiDTO.Response.Success(result)
    }

    @DeleteMapping("/{id}")
    @Auth
    @Policy(permissions = ["category-management"])
    fun deleteCategory(@PathVariable("id") @Valid @UUID id: String): ApiDTO.Response.Success<String> {
        categoryService.deleteCategory(id)

        return ApiDTO.Response.Success("Deleted Successfully")
    }
}