package com.example.maktab.module.book.controller

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
//import org.springframework.web.bind.annotation.ResponseBody # Question
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/book")
@Validated
class BookController(
    val bookService: BookService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody @Valid createDto: BookDTO.Request.Create): BookDTO.Response.Created {
        return bookService.createBook(createDto)
    }

    @GetMapping
    // @Valid @RequestParam searchDto: BookDTO.Request.Search #Question
    fun getAllBooks(): BookDTO.Response.RetrievedAll {
        return bookService.getAllBooks()
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") id: String): BookDTO.Response.RetrievedOne {
        return bookService.getBookById(id)
    }

    @PatchMapping("/{id}")
    fun updateBook(
        @Valid @PathVariable("id") @UUID id: String,
        @Valid @RequestBody updateDto: BookDTO.Request.Update,
    ): BookDTO.Response.Updated {
        return bookService.updateBook(id, updateDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@Valid @PathVariable("id") @UUID id: String) {
        bookService.deleteBook(id)
    }
}