package com.example.maktab.module.book.controller

import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.book.dto.BookDTO
import com.example.maktab.module.book.service.BookService
import jakarta.validation.Valid
import org.hibernate.validator.constraints.UUID
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/book")
@Validated
class BookController(
    val bookService: BookService,
) {
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(
        @RequestBody @Valid createDto: BookDTO.Request.Create
    ): ApiDTO.Response.Success<BookDTO.Response.Created> {
        val result = bookService.createBook(createDto);

        return ApiDTO.Response.Success(result, status = HttpStatus.CREATED)
    }

    @GetMapping
    @ResponseBody
    // @RequestParam @Valid searchDto: BookDTO.Request.Search #Question
    fun getAllBooks(): ApiDTO.Response.Success<BookDTO.Response.RetrievedAll> {
        val result = bookService.getAllBooks()

        return ApiDTO.Response.Success(result)
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getBookById(@PathVariable("id") id: String): ApiDTO.Response.Success<BookDTO.Response.RetrievedOne> {
        val result = bookService.getBookById(id);

        return ApiDTO.Response.Success(result)
    }

    @PatchMapping("/{id}")
    @ResponseBody
    fun updateBook(
        @PathVariable("id") @Valid @UUID id: String,
        @RequestBody @Valid updateDto: BookDTO.Request.Update
    ): ApiDTO.Response.Success<BookDTO.Response.Updated> {
        val result = bookService.updateBook(id, updateDto);

        return ApiDTO.Response.Success(result)
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable("id") @Valid @UUID id: String): ApiDTO.Response.Success<String> {
        bookService.deleteBook(id)

        return ApiDTO.Response.Success("Deleted Successfully")
    }
}