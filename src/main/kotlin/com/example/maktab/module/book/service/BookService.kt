package com.example.maktab.module.book.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.book.dto.BookDTO
import com.example.maktab.module.book.dto.CreateBookRequestDTO
import com.example.maktab.module.book.dto.FilterBookRequestDTO
import com.example.maktab.module.book.dto.UpdateBookRequestDTO
import com.example.maktab.module.book.entity.BookEntity
import com.example.maktab.module.book.mapper.BookMapper
import com.example.maktab.module.book.repository.BookRepository
import com.example.maktab.module.book.specification.BookSpecification
import com.example.maktab.module.category.service.CategoryService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    val bookRepository: BookRepository,
    val bookMapper: BookMapper,
    val categoryService: CategoryService,
) {
    private val logger = LoggerFactory.getLogger(BookService::class.simpleName)

    @Transactional
    fun createBook(createDto: CreateBookRequestDTO): BookDTO {
        val categories = categoryService.findAllByIds(createDto.categories)

        if (categories.isEmpty()) throw ApiError.BadRequest("At least one category is required")

        val book = bookRepository.save(createDto.let {
            BookEntity(
                title = it.title,
                description = it.description,
                price = it.price,
                categories = categories.toMutableSet()
            )
        })

        logger.info("Book created with id ${book.id}")

        return bookMapper.toDto(book)
    }

    @Transactional(readOnly = true)
    fun getAllBooks(filterDto: FilterBookRequestDTO): List<BookDTO> {
        val books = bookRepository.findAll(BookSpecification.filter(filterDto))

        return bookMapper.toDto(books)
    }

    fun getBook(id: String): BookDTO {
        val book = findByIdOrThrow(id)

        return bookMapper.toDto(book)
    }

    fun getBookById(id: String): BookDTO {
        val book = findByIdOrThrow(id)

        return bookMapper.toDto(book)
    }

    fun findByIdOrThrow(id: String): BookEntity {
        return bookRepository.findById(id).orElseThrow { ApiError.NotFound("Invalid Id") }
    }

    @Transactional
    fun updateBook(id: String, updateDto: UpdateBookRequestDTO): BookDTO {
        val book = findByIdOrThrow(id)
        val categories = categoryService.findAllByIds(updateDto.categories)

        if (categories.isEmpty()) throw ApiError.BadRequest("At least one category is required")

        book.apply {
            this.title = updateDto.title
            this.description = updateDto.description
            this.price = updateDto.price
            this.categories = categories.toMutableSet()
        }

        bookRepository.save(book)

        logger.info("The book $id updated successfully.")

        return bookMapper.toDto(book)
    }

    @Transactional
    fun deleteBook(id: String) {
        val book = findByIdOrThrow(id)

        bookRepository.delete(book)

        logger.info("The book $id removed successfully.")
    }
}