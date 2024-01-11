package com.example.maktab.integration

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BookControllerIntegrationTest(@Autowired val client: TestRestTemplate) {
    @Test
    fun `find all books`() {
        val entity = client.getForEntity("/v1/book", String::class.java)

        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `find book with id`() {
        val entity = client.getForEntity("/v1/book/1", String::class.java)

        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("Book-1")
    }
}