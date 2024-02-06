package com.example.maktab.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object ApiError {
    data class Custom(
        override val message: String,
        val status: HttpStatus = HttpStatus.OK
    ) : ResponseStatusException(status, message)

    data class BadRequest(
        override val message: String = "Bad Request",
        val status: HttpStatus = HttpStatus.BAD_REQUEST
    ) : ResponseStatusException(status, message)

    data class Conflict(
        override val message: String = "Conflict",
        val status: HttpStatus = HttpStatus.CONFLICT
    ) : ResponseStatusException(status, message)

    data class NotFound(
        override val message: String = "Not Found Error",
        val status: HttpStatus = HttpStatus.NOT_FOUND
    ) : ResponseStatusException(status, message)

    data class Unauthorized(
        override val message: String = "Unauthorized Request",
        val status: HttpStatus = HttpStatus.FORBIDDEN
    ) : ResponseStatusException(status, message)
}