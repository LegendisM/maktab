package com.example.maktab.module.book.repository

import com.example.maktab.module.book.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookEntity, String> {}