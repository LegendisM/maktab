package com.example.maktab.module.book.repository

import com.example.maktab.module.book.entity.BookEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookEntity, String> {
//    @Query("SELECT * FROM ") --> # Question
}