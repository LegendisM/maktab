package com.example.maktab.unit

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

import com.example.maktab.module.book.service.BookService
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
        given(bookService.findAll()).willReturn(listOf("Book-Three", "Book-Four"))

        mockMvc.get("/v1/book")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.[0]") { value("Book-Three") }
                jsonPath("\$.[1]") { value("Book-Four") }
            }
    }

    @Test
    fun `find book with id`() {
        given(bookService.findById("1")).willReturn("Book-1")

        mockMvc.get("/v1/book/1")
            .andExpect { status { isOk() } }
            .andExpect { content { string("Book-1") } }
    }

    @Test
    fun `remove one book with id`() {
        given(bookService.removeOne("1")).willReturn("The Book with ID 1 Deleted Successfully")

        val result = mockMvc.delete("/v1/book/1")
            .andReturn()

        assertThat(result.response.status).isEqualTo(HttpStatus.OK.value())
        assertThat(result.response.contentAsString).isEqualTo("The Book with ID 1 Deleted Successfully")
    }
}