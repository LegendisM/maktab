package com.example.maktab.module.book.controller

import com.example.maktab.common.annotation.Auth
import com.example.maktab.common.annotation.Policy
import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.book.dto.BookDTO
import com.example.maktab.module.book.dto.CreateBookRequestDTO
import com.example.maktab.module.book.dto.FilterBookRequestDTO
import com.example.maktab.module.book.dto.UpdateBookRequestDTO
import com.example.maktab.module.book.service.BookService
import jakarta.validation.Valid
import org.hibernate.validator.constraints.UUID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
@RequestMapping("/v1/books")
class BookController(
    private val bookService: BookService
) {
    @PostMapping
    @Auth
    @Policy(permissions = ["book-management"])
    fun createBook(
        @RequestBody @Valid createDto: CreateBookRequestDTO
    ): ApiDTO.Response.Success<BookDTO> {
        val result = bookService.createBook(createDto)

        return ApiDTO.Response.Success(result, status = HttpStatus.CREATED)
    }

    @PostMapping("/filter")
    fun getAllBooks(
        @RequestBody @Valid filterDto: FilterBookRequestDTO,
        @PageableDefault page: Pageable
    ): ApiDTO.Response.Success<Page<BookDTO>> {
        val result = bookService.getAllBooks(filterDto, page)

        return ApiDTO.Response.Success(result)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") @Valid @UUID id: String): ApiDTO.Response.Success<BookDTO> {
        val result = bookService.getBookById(id)

        return ApiDTO.Response.Success(result)
    }

    @PatchMapping("/{id}")
    @Auth
    @Policy(permissions = ["book-management"])
    fun updateBook(
        @PathVariable("id") @Valid @UUID id: String,
        @RequestBody @Valid updateDto: UpdateBookRequestDTO
    ): ApiDTO.Response.Success<BookDTO> {
        val result = bookService.updateBook(id, updateDto)

        return ApiDTO.Response.Success(result)
    }

    @DeleteMapping("/{id}")
    @Auth
    @Policy(permissions = ["book-management"])
    fun deleteBook(@PathVariable("id") @Valid @UUID id: String): ApiDTO.Response.Success<String> {
        bookService.deleteBook(id)

        return ApiDTO.Response.Success("Deleted Successfully")
    }
}