package com.example.maktab.module.category.controller

import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.category.dto.CategoryDto
import com.example.maktab.module.category.dto.CreateCategoryRequestDto
import com.example.maktab.module.category.dto.UpdateCategoryRequestDto
import com.example.maktab.module.category.service.CategoryService
import jakarta.validation.Valid
import org.hibernate.validator.constraints.UUID
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
@RequestMapping("/api/v1/categories")
class CategoryController(
    val categoryService: CategoryService
) {
    @PostMapping
    fun createCategory(
        @RequestBody @Valid createDto: CreateCategoryRequestDto
    ): ApiDTO.Response.Success<CategoryDto> {
        val result = categoryService.createCategory(createDto)

        return ApiDTO.Response.Success(result, status = HttpStatus.CREATED)
    }

    @PostMapping("/filter")
    fun getAllCategories(): ApiDTO.Response.Success<List<CategoryDto>> {
        val result = categoryService.getAllCategories()

        return ApiDTO.Response.Success(result)
    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable("id") id: String): ApiDTO.Response.Success<CategoryDto> {
        val category = categoryService.getCategoryById(id)

        return ApiDTO.Response.Success(category)
    }

    @PatchMapping("/{id}")
    fun updateCategory(
        @PathVariable("id") @Valid @UUID id: String,
        @RequestBody @Valid updateDto: UpdateCategoryRequestDto
    ): ApiDTO.Response.Success<CategoryDto> {
        val result = categoryService.updateCategory(id, updateDto)

        return ApiDTO.Response.Success(result)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable("id") @Valid @UUID id: String): ApiDTO.Response.Success<String> {
        categoryService.deleteCategory(id)

        return ApiDTO.Response.Success("Deleted Successfully")
    }
}