package com.example.maktab.common.interceptor

import com.example.maktab.common.annotation.Auth
import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.auth.enums.AuthTokenType
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

        /*
        * Found auth annotation on class level (apply to all methods)
        * OR found auth annotation from single function level
        * */
        if (
            handler.bean.javaClass.isAnnotationPresent(Auth::class.java) ||
            handler.hasMethodAnnotation(Auth::class.java)
        ) {
            val token = request.getHeader("Authorization")
                .split("Bearer")
                .getOrElse(1) {
                    throw ApiError.Unauthorized("Invalid Authorization Token")
                }

            /*
            * Validate token or throw unauthorized exception
            * */
            try {
                val user = authTokenService.validateToken(AuthTokenType.ACCESS, token)
                request.setAttribute("user", user)
            } catch (error: Exception) {
                throw ApiError.Unauthorized("Invalid Authentication")
            }

            return true
        } else {
            return true
        }
    }
}