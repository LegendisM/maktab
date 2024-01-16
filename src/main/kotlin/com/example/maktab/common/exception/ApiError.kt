package com.example.maktab.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object ApiError {
    class Custom(
        override val message: String,
        status: HttpStatus = HttpStatus.OK
    ) : ResponseStatusException(status, message)

    class BadRequest(
        override val message: String = "Bad Request",
        status: HttpStatus = HttpStatus.BAD_REQUEST
    ) : ResponseStatusException(status, message)

    class NotFound(
        override val message: String = "Not Found Error",
        status: HttpStatus = HttpStatus.NOT_FOUND
    ) : ResponseStatusException(status, message)

    class Unauthorized(
        override val message: String = "Unauthorized Request",
        status: HttpStatus = HttpStatus.FORBIDDEN
    ) : ResponseStatusException(status, message)
}