package com.example.maktab.unit

import com.example.maktab.common.dto.PaginationResponseDTO
import com.example.maktab.module.book.dto.BookDTO
import com.example.maktab.module.book.dto.FilterBookRequestDTO
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

import com.example.maktab.module.book.service.BookService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get

@WebMvcTest
class BookControllerUnitTest(@Autowired val mockMvc: MockMvc) {
    @MockBean
    lateinit var bookService: BookService

    @Test
    fun `find all books`() {
        val book = BookDTO(
            id = "u-u-i-d",
            title = "hello",
            description = "good",
            price = 1000,
            categories = setOf(),
        )
        given(bookService.getAllBooks(
            FilterBookRequestDTO("hello", null, null, null),
            page = Pageable.ofSize(2)
        )).willReturn(
            PaginationResponseDTO(
                items = listOf(book),
                size = 1,
                page = 1,
                total = 1
            )
        )

        mockMvc.get("/v1/book")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
//            .andExpect {
//                jsonPath("\$.[0]") { value("Book-Three") }
//                jsonPath("\$.[1]") { value("Book-Four") }
//            }
    }

    @Test
    fun `find book with id`() {
        val book = BookDTO(
            id = "u-u-i-d",
            title = "hello",
            description = "good",
            price = 1000,
            categories = setOf(),
        )
        given(bookService.getBookById("u-u-i-d")).willReturn(book)

        mockMvc.get("/v1/book/1")
            .andExpect { status { isOk() } }
            .andExpect { content { string(book.toString()) } }
    }

    @Test
    fun `remove one book with id`() {
        given(bookService.deleteBook("1")).willReturn(Unit)

        val result = mockMvc.delete("/v1/book/1")
            .andReturn()

        assertThat(result.response.status).isEqualTo(HttpStatus.OK.value())
    }
}