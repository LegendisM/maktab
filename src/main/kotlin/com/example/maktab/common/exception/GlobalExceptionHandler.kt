package com.example.maktab.common.exception

import com.example.maktab.common.dto.ApiDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun resolveDefaultException(
        exception: Exception,
        request: WebRequest
    ): ApiDTO.Response.Error {
        return ApiDTO.Response.Error(
            message = exception.message ?: "Internal Server Error",
            status = HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(ResponseStatusException::class)
    fun resolveResponseStatusException(
        exception: ResponseStatusException,
        request: WebRequest
    ): ApiDTO.Response.Error {
        return ApiDTO.Response.Error(
            message = exception.message,
            status = HttpStatus.valueOf(exception.statusCode.value())
        )
    }
}