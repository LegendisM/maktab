package com.example.maktab.module.book.service

import com.example.maktab.module.book.dto.BookDTO
import com.example.maktab.module.book.entity.BookEntity
import com.example.maktab.module.book.repository.BookRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository,
) {
    private val logger = LoggerFactory.getLogger(BookService::class.simpleName)

    fun createBook(createDto: BookDTO.Request.Create): BookDTO.Response.Created {
        val book = bookRepository.save(createDto.let {
            BookEntity(
                id = "",
                title = it.title,
                description = it.description,
                price = it.price,
            )
        })

        logger.info("Book created with id ${book.id}")

        return book.let {
            BookDTO.Response.Created(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price
            )
        }
    }

    fun getAllBooks(searchDto: BookDTO.Request.Search): BookDTO.Response.RetrievedAll {
        val books = bookRepository.findAll()

        return BookDTO.Response.RetrievedAll(items = books.map {
            BookDTO.Response.RetrievedOne(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price
            )
        })
    }

    fun getBook(): BookDTO.Response.RetrievedOne {
        return BookDTO.Response.RetrievedOne(
            id = "1",
            title = "a",
            description = "b",
            price = 999,
        )
    }

    fun getBookById(id: String): BookDTO.Response.RetrievedOne {
        val book = bookRepository.findByIdOrNull(id)

        return book?.let {
            BookDTO.Response.RetrievedOne(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
            )
        } ?: throw Exception("Invalid Id")
    }

    fun updateBook(id: String): String {
        logger.info("Book $id updated.")
        return "The Book with ID $id Updated Successfully"
    }

    fun deleteBook(id: String): String {
        logger.warn("Book $id removed.")
        return "The Book with ID $id Deleted Successfully"
    }
}