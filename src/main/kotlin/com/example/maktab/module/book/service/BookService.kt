package com.example.maktab.module.book.service

import com.example.maktab.module.book.BookRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository,
) {
    private val logger = LoggerFactory.getLogger(BookService::class.simpleName)

    fun createBook(): String {
        logger.info("New Book Created with ID 1.");
        return "Book Created Successfully with ID 1"
    }

    fun getBook(): String {
        return "Book-One"
    }

    fun getBookById(id: String): String {
        return "Book-$id"
    }

    fun updateBook(id: String): String {
        logger.info("Book $id updated.")
        return "The Book with ID $id Updated Successfully"
    }

    fun deleteBook(id: String): String {
        logger.warn("Book $id removed.")
        return "The Book with ID $id Deleted Successfully"
    }

    fun getAllBooks(): List<String> {
        return listOf("Book-One", "Book-Two")
    }
}