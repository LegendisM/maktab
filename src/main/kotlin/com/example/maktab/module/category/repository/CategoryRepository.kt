package com.example.maktab.module.category.repository

import com.example.maktab.module.book.entity.BookEntity
import com.example.maktab.module.category.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CategoryRepository : JpaRepository<CategoryEntity, String>, JpaSpecificationExecutor<CategoryEntity>