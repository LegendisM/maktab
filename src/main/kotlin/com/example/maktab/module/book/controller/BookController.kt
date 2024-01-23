package com.example.maktab.module.book.controller

import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.book.dto.BookDto
import com.example.maktab.module.book.dto.CreateBookRequestDto
import com.example.maktab.module.book.dto.FilterBookRequestDto
import com.example.maktab.module.book.dto.UpdateBookRequestDto
import com.example.maktab.module.book.service.BookService
import jakarta.validation.Valid
import org.hibernate.validator.constraints.UUID
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
@RequestMapping("/api/v1/book")
class BookController(
    val bookService: BookService
) {
    @PostMapping
    fun createBook(
        @RequestBody @Valid createDto: CreateBookRequestDto
    ): ApiDTO.Response.Success<BookDto> {
        val result = bookService.createBook(createDto);

        return ApiDTO.Response.Success(result, status = HttpStatus.CREATED)
    }

    @PostMapping("/filter")
    fun getAllBooks(@RequestBody @Valid filterDto: FilterBookRequestDto): ApiDTO.Response.Success<List<BookDto>> {
        val result = bookService.getAllBooks(filterDto)

        return ApiDTO.Response.Success(result)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") @Valid @UUID id: String): ApiDTO.Response.Success<BookDto> {
        val result = bookService.getBookById(id);

        return ApiDTO.Response.Success(result)
    }

    @PatchMapping("/{id}")
    fun updateBook(
        @PathVariable("id") @Valid @UUID id: String,
        @RequestBody @Valid updateDto: UpdateBookRequestDto
    ): ApiDTO.Response.Success<BookDto> {
        val result = bookService.updateBook(id, updateDto);

        return ApiDTO.Response.Success(result)
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable("id") @Valid @UUID id: String): ApiDTO.Response.Success<String> {
        bookService.deleteBook(id)

        return ApiDTO.Response.Success("Deleted Successfully")
    }
}