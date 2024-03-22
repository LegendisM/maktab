package com.example.maktab.common.exception

import com.example.maktab.common.dto.ApiDTO
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.server.ResponseStatusException

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
            message = "${exception.name} should be a valid ${exception.requiredType?.simpleName ?: "field"} and ${exception.value} isn't",
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

    @ExceptionHandler(EntityNotFoundException::class)
    fun resolveEntityNotFoundException(
        exception: EntityNotFoundException,
        request: WebRequest
    ): ApiDTO.Response.Error {
        return ApiDTO.Response.Error(
            message = "Unable to find your target entity with this id",
            status = HttpStatus.NOT_FOUND
        )
    }
}