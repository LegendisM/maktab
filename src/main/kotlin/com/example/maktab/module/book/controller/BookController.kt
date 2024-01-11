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
    @GetMapping()
    fun findAll(): List<String> {
        return bookService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): String {
        return bookService.findById(id)
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun createOne(): String {
        return bookService.create()
    }

    @PatchMapping("/{id}")
    fun updateOne(@PathVariable("id") id: String): String {
        return bookService.updateOne(id)
    }

    @DeleteMapping("/{id}")
    fun removeOne(@PathVariable("id") id: String): String {
        return bookService.removeOne(id)
    }
}