package com.example.maktab.module.book.repository

import com.example.maktab.module.book.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<BookEntity, String>, JpaSpecificationExecutor<BookEntity>