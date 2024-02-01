package com.example.maktab.common.exception

import com.example.maktab.common.dto.ApiDTO
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors

@ControllerAdvice
class GlobalExceptionHandler {
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

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun resolveMethodArgumentTypeMismatchException(
        exception: MethodArgumentTypeMismatchException,
        request: WebRequest
    ): ApiDTO.Response.Error {
        return ApiDTO.Response.Error(
            message = exception.message ?: "Argument Type Mismatch",
            status = HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun resolveMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException,
        request: WebRequest
    ): ApiDTO.Response.Error {
        return ApiDTO.Response.Error(
            message = "Validation Error",
            status = HttpStatus.BAD_REQUEST,
            errors = exception.fieldErrors.map {
                "${it.field} ${it.defaultMessage ?: "has validation error"}"
            }
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun resolveHttpMessageNotReadableException(
        exception: HttpMessageNotReadableException,
        request: WebRequest
    ): ApiDTO.Response.Error {
        return ApiDTO.Response.Error(
            message = exception.message ?: "Validation Error (Invalid Json Property Key/Value)",
            status = HttpStatus.BAD_REQUEST
        )
    }

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
}