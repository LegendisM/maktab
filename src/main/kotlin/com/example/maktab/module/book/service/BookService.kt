package com.example.maktab.module.book.service

import com.example.maktab.common.dto.PaginationResponseDTO
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
import com.example.maktab.module.storage.service.StorageResourceService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val bookMapper: BookMapper,
    private val categoryService: CategoryService,
    private val storageResourceService: StorageResourceService
) {
    private val logger = LoggerFactory.getLogger(BookService::class.simpleName)

    @Transactional
    fun createBook(createDto: CreateBookRequestDTO): BookDTO {
        val categories = categoryService.findAllByIds(createDto.categories)
        val image = storageResourceService.findByIdOrThrow(createDto.imageId)

//        if (categories.isEmpty()) throw ApiError.BadRequest("At least one category is required")

        val book = bookRepository.save(createDto.let {
            BookEntity(
                title = it.title,
                description = it.description,
                price = it.price,
                image = image,
                categories = categories.toMutableSet()
            )
        })

        logger.info("Book created with id ${book.id}")

        return bookMapper.toDto(book)
    }

    @Transactional(readOnly = true)
    fun getAllBooks(filterDto: FilterBookRequestDTO, page: Pageable): PaginationResponseDTO<BookDTO> {
        val books = bookRepository.findAll(BookSpecification.filter(filterDto), page)

        return PaginationResponseDTO(
            items = bookMapper.toDtos(books.toList()),
            size = books.size,
            page = page.pageNumber,
            total = books.totalPages
        )
    }

    @Transactional(readOnly = true)
    fun getBook(id: String): BookDTO {
        val book = this.findByIdOrThrow(id)

        return bookMapper.toDto(book)
    }

    @Transactional(readOnly = true)
    fun getBookById(id: String): BookDTO {
        val book = this.findByIdOrThrow(id)

        return bookMapper.toDto(book)
    }

    @Transactional(readOnly = true)
    fun findByIdOrThrow(id: String): BookEntity {
        return bookRepository.findById(id).orElseThrow { ApiError.NotFound("Invalid Id") }
    }

    @Transactional
    fun updateBook(id: String, updateDto: UpdateBookRequestDTO): BookDTO {
        val book = this.findByIdOrThrow(id)
        val categories = categoryService.findAllByIds(updateDto.categories)
        val image = storageResourceService.findByIdOrThrow(updateDto.imageId)

        if (categories.isEmpty()) throw ApiError.BadRequest("At least one category is required")

        book.apply {
            this.title = updateDto.title
            this.description = updateDto.description
            this.price = updateDto.price
            this.categories = categories.toMutableSet()
            this.image = image
        }

        bookRepository.save(book)

        logger.info("The book $id updated successfully.")

        return bookMapper.toDto(book)
    }

    @Transactional
    fun deleteBook(id: String) {
        val book = this.findByIdOrThrow(id)

        bookRepository.delete(book)

        logger.info("The book $id removed successfully.")
    }
}