package com.example.maktab.module.book

import com.example.maktab.module.book.entity.BookEntity
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookEntity, String> {}