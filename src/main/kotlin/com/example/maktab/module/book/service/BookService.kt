package com.example.maktab.module.book.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class BookService {
    @Value("\${secret.my-key}")
    lateinit var sampleKey: String;

    private val logger = LoggerFactory.getLogger(BookService::class.simpleName)

    fun create(): String {
        logger.info("New Book Created with ID 1.");
        return "Book Created Successfully with ID 1"
    }

    fun findAll(): List<String> {
        return listOf("Book-One", "Book-Two", sampleKey)
    }

    fun findOne(): String {
        return "Book-One"
    }

    fun findById(id: String): String {
        return "Book-$id"
    }

    fun updateOne(id: String): String {
        logger.info("Book $id updated.")
        return "The Book with ID $id Updated Successfully"
    }

    fun removeOne(id: String): String {
        logger.warn("Book $id removed.")
        return "The Book with ID $id Deleted Successfully"
    }
}