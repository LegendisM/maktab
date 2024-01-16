package com.example.maktab.module.book.service

import com.example.maktab.common.exception.ApiError
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
        val book = bookRepository.save(
            createDto.let {
                BookEntity(
                    id = "",
                    title = it.title,
                    description = it.description,
                    price = it.price
                )
            }
        )

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

    fun getAllBooks(): BookDTO.Response.RetrievedAll {
        val books = bookRepository.findAll()

        return BookDTO.Response.RetrievedAll(
            items = books.map {
                BookDTO.Response.RetrievedOne(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    price = it.price
                )
            }
        )
    }

    fun getBook(id: String): BookDTO.Response.RetrievedOne {
        val book = bookRepository.findByIdOrNull(id) ?: throw ApiError.NotFound("Invalid Book Id")

        return book.let {
            BookDTO.Response.RetrievedOne(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price
            )
        }
    }

    fun getBookById(id: String): BookDTO.Response.RetrievedOne {
        val book = bookRepository.findByIdOrNull(id)

        return book?.let {
            BookDTO.Response.RetrievedOne(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price
            )
        } ?: throw ApiError.NotFound("Invalid Id")
    }

    fun updateBook(id: String, updateDto: BookDTO.Request.Update): BookDTO.Response.Updated {
        val book = bookRepository.findByIdOrNull(id) ?: throw ApiError.NotFound("Invalid Book Id")

        book.apply {
            updateDto.let {
                this.title = it.title
                this.description = it.description
                this.price = it.price
            }
        }

        bookRepository.save(book)

        logger.info("The book $id updated successfully.")

        return book.let {
            BookDTO.Response.Updated(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price
            )
        }
    }

    fun deleteBook(id: String) {
        val book = bookRepository.findByIdOrNull(id) ?: throw ApiError.NotFound("Invalid Book Id")

        bookRepository.delete(book)

        logger.info("The book $id removed successfully.")
    }
}