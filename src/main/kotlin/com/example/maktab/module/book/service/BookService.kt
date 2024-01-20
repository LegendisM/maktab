package com.example.maktab.module.book.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.book.dto.BookDto
import com.example.maktab.module.book.dto.CreateBookRequestDto
import com.example.maktab.module.book.dto.FilterBookRequestDto
import com.example.maktab.module.book.dto.UpdateBookRequestDto
import com.example.maktab.module.book.entity.BookEntity
import com.example.maktab.module.book.mapper.BookMapper
import com.example.maktab.module.book.repository.BookRepository
import com.example.maktab.module.book.specification.BookSpecification
import org.hibernate.query.Order
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    val bookRepository: BookRepository,
    val bookMapper: BookMapper,
) {
    private val logger = LoggerFactory.getLogger(BookService::class.simpleName)

    @Transactional
    fun createBook(createDto: CreateBookRequestDto): BookDto {
        val book = bookRepository.save(
            bookMapper.fromCreateDtoToEntity(createDto)
        )

        logger.info("Book created with id ${book.id}")

        return bookMapper.toDto(book)
    }

    @Transactional(readOnly = true)
    fun getAllBooks(filterDto: FilterBookRequestDto): List<BookDto> {
        val spec = BookSpecification(filterDto)
        val books = bookRepository.findAll(spec)

        return bookMapper.toDto(books)
    }

    fun getBook(id: String): BookDto {
        val book = this.getBookByIdOrThrow(id)

        return bookMapper.toDto(book)
    }

    fun getBookById(id: String): BookDto {
        val book = this.getBookByIdOrThrow(id)

        return bookMapper.toDto(book)
    }

    fun getBookByIdOrThrow(id: String): BookEntity {
        return bookRepository.findById(id).orElseThrow { ApiError.NotFound("Invalid Id") }
    }

    @Transactional
    fun updateBook(id: String, updateDto: UpdateBookRequestDto): BookDto {
        val book = this.getBookByIdOrThrow(id)

        book.apply {
            this.title = updateDto.title
            this.description = updateDto.description
            this.price = updateDto.price
        }

        bookRepository.save(book)

        logger.info("The book $id updated successfully.")

        return bookMapper.toDto(book)
    }

    @Transactional
    fun deleteBook(id: String) {
        val book = this.getBookByIdOrThrow(id)

        bookRepository.delete(book)

        logger.info("The book $id removed successfully.")
    }
}