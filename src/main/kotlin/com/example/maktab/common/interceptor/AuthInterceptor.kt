package com.example.maktab.common.interceptor

import com.example.maktab.common.annotation.Auth
import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.auth.service.AuthTokenService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

class AuthInterceptor(
    private val authTokenService: AuthTokenService
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val handler = handler as HandlerMethod

        if (handler.hasMethodAnnotation(Auth::class.java)) {
            val token = request.getHeader("Authorization")
                .split("Bearer")
                .getOrElse(1) {
                    throw ApiError.Conflict("Invalid Authorization Token")
                }

            // Validate token or throw invalid token exception
            val payload = authTokenService.validateToken(token)

            request.setAttribute("user", payload)

            return true
        } else {
            return true
        }
    }
}