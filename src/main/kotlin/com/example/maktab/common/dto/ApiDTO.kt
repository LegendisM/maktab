package com.example.maktab.common.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ApiDTO {
    object Response {
        data class Properties<T>(
            var state: Boolean,
            var data: T,
            var message: String,
            var status: Int
        )

        open class Default<T>(
            val properties: Properties<T>,
            status: HttpStatus
        ) : ResponseEntity<Any>(properties, status)

        class Success<T>(
            data: T,
            state: Boolean = true,
            message: String = "Successfully",
            status: HttpStatus = HttpStatus.OK
        ) : Default<T>(
            status = status,
            properties = Properties(state = state, data = data, message = message, status = status.value()),
        )

        class Error(
            message: String = "Internal Server Error",
            status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
            errors: List<String> = listOf()
        ) : Default<List<String>>(
            status = status,
            properties = Properties(state = false, data = errors, message = message, status = status.value()),
        )
    }
}