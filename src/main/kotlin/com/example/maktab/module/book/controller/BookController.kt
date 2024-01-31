package com.example.maktab.module.book.controller

import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.common.dto.PaginationResponseDTO
import com.example.maktab.module.book.dto.BookDTO
import com.example.maktab.module.book.dto.CreateBookRequestDTO
import com.example.maktab.module.book.dto.FilterBookRequestDTO
import com.example.maktab.module.book.dto.UpdateBookRequestDTO
import com.example.maktab.module.book.service.BookService
import jakarta.validation.Valid
import org.hibernate.validator.constraints.UUID
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/book")
class BookController(
    val bookService: BookService
) {
    @PostMapping
    fun createBook(
        @RequestBody @Valid createDto: CreateBookRequestDTO
    ): ApiDTO.Response.Success<BookDTO> {
        val result = bookService.createBook(createDto)

        return ApiDTO.Response.Success(result, status = HttpStatus.CREATED)
    }

    @PostMapping("/filter")
    fun getAllBooks(
        @RequestBody @Valid filterDto: FilterBookRequestDTO,
        page: Pageable
    ): ApiDTO.Response.Success<PaginationResponseDTO<BookDTO>> {
        val result = bookService.getAllBooks(filterDto, page)

        return ApiDTO.Response.Success(result)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") @Valid @UUID id: String): ApiDTO.Response.Success<BookDTO> {
        val result = bookService.getBookById(id)

        return ApiDTO.Response.Success(result)
    }

    @PatchMapping("/{id}")
    fun updateBook(
        @PathVariable("id") @Valid @UUID id: String,
        @RequestBody @Valid updateDto: UpdateBookRequestDTO
    ): ApiDTO.Response.Success<BookDTO> {
        val result = bookService.updateBook(id, updateDto)

        return ApiDTO.Response.Success(result)
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable("id") @Valid @UUID id: String): ApiDTO.Response.Success<String> {
        bookService.deleteBook(id)

        return ApiDTO.Response.Success("Deleted Successfully")
    }
}