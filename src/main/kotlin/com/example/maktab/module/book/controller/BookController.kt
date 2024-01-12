package com.example.maktab.module.book.controller

import com.example.maktab.module.book.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/book")
class BookController(
    val bookService: BookService,
) {
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(): String {
        return bookService.createBook()
    }

    @GetMapping()
    fun getAllBooks(): List<String> {
        return bookService.getAllBooks()
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") id: String): String {
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