package com.example.maktab.module.book.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.book.constant.BookConstant
import com.example.maktab.module.book.dto.BookDTO
import com.example.maktab.module.book.dto.CreateBookRequestDTO
import com.example.maktab.module.book.dto.FilterBookRequestDTO
import com.example.maktab.module.book.dto.UpdateBookRequestDTO
import com.example.maktab.module.book.entity.BookEntity
import com.example.maktab.module.book.mapper.BookMapper
import com.example.maktab.module.book.repository.BookRepository
import com.example.maktab.module.book.specification.BookSpecification
import com.example.maktab.module.category.entity.CategoryEntity
import com.example.maktab.module.category.service.CategoryService
import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.example.maktab.module.storage.service.StorageResourceService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
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
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun createBook(createDto: CreateBookRequestDTO): BookDTO {
        val document = storageResourceService.findByIdOrThrow(createDto.documentId, "Invalid document")
        val images = storageResourceService.findAllByIds(createDto.imageIds)
        val categories = categoryService.findAllByIds(createDto.categoryIds)

        // * Validate resources count & content-type
        this.validateBookRequiredResources(document, images, categories)

        val book = this.saveBook(createDto.let {
            BookEntity(
                title = it.title,
                description = it.description,
                price = it.price,
                document = document,
                images = images.toMutableList(),
                categories = categories.toMutableList()
            )
        })

        logger.info("Book created with id ${book.id}")

        return bookMapper.toDto(book)
    }

    @Transactional(readOnly = true)
    fun getAllBooks(filterDto: FilterBookRequestDTO, page: Pageable): Page<BookDTO> {
        val books = bookRepository.findAll(BookSpecification.filter(filterDto), page)

        return books.map { bookMapper.toDto(it) }
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
        val document = storageResourceService.findByIdOrThrow(updateDto.documentId, "Invalid document")
        val categories = categoryService.findAllByIds(updateDto.categoryIds)
        val images = storageResourceService.findAllByIds(updateDto.imageIds)

        // * Validate resources count & content-type
        this.validateBookRequiredResources(document, images, categories)

        book.apply {
            this.title = updateDto.title
            this.description = updateDto.description
            this.price = updateDto.price
            this.document = document
            this.images = images.toMutableList()
            this.categories = categories.toMutableList()
        }

        saveBook(book)

        logger.info("The book $id updated successfully.")

        return bookMapper.toDto(book)
    }

    @Transactional
    fun deleteBook(id: String) {
        val book = this.findByIdOrThrow(id)

        bookRepository.delete(book)

        logger.info("The book $id removed successfully.")
    }

    @Transactional
    fun saveBook(book: BookEntity): BookEntity = bookRepository.save(book)

    fun validateBookRequiredResources(
        document: StorageResourceEntity,
        images: List<StorageResourceEntity>,
        categories: List<CategoryEntity>
    ) {
        if (images.isEmpty()) throw ApiError.BadRequest("At least one image is required")
        if (categories.isEmpty()) throw ApiError.BadRequest("At least one category is required")
        if (!BookConstant.VALID_DOCUMENT_CONTENT_TYPES.contains(document.contentType)) throw ApiError.BadRequest("Invalid resource document content type")
        if (images.any { !BookConstant.VALID_IMAGE_CONTENT_TYPES.contains(it.contentType) }) throw ApiError.BadRequest("Invalid resource image content type")
    }
}