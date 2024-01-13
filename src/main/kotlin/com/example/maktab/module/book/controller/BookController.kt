package com.example.maktab.module.book.controller

import com.example.maktab.module.book.dto.BookDTO
import com.example.maktab.module.book.service.BookService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
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
import java.awt.print.Book

@RestController
@RequestMapping("/v1/book") // # Question
class BookController(
    val bookService: BookService,
) {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody @Valid createDto: BookDTO.Request.Create): BookDTO.Response.Created {
        return bookService.createBook(createDto)
    }

    @GetMapping()
    fun getAllBooks(@Valid @RequestBody searchDto: BookDTO.Request.Search): BookDTO.Response.RetrievedAll {
        return bookService.getAllBooks(searchDto)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") id: String): BookDTO.Response.RetrievedOne {
        return bookService.getBookById(id)
    }

    @PatchMapping("/{id}")
    fun updateBook(@PathVariable("id") id: String): String {
        return bookService.updateBook(id)
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable("id") id: String): String {
        return bookService.deleteBook(id)
    }
}