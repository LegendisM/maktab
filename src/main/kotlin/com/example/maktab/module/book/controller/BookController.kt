package com.example.maktab.module.book.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/book")
class BookController {
    @GetMapping("/")
    fun findAll(): List<String> {
        return listOf("Book-One", "Book-Two")
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable("id") id: String): String {
        return "Book-One";
    }

    fun createOne() {}

    fun updateOne() {}

    fun removeOne() {}
}