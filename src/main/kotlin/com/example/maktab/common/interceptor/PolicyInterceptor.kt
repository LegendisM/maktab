package com.example.maktab.common.interceptor

import com.example.maktab.common.annotation.Policy
import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.user.configuration.PolicyConfiguration
import com.example.maktab.module.user.model.UserModel
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

class PolicyInterceptor(
    private val policyConfiguration: PolicyConfiguration
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val handler = handler as HandlerMethod

        if (
            handler.javaClass.isAnnotationPresent(Policy::class.java) ||
            handler.hasMethodAnnotation(Policy::class.java)
        ) {
            val policy = handler.getMethodAnnotation(Policy::class.java)!!
            val policyRoles = policy.roles
            val policyPermissions = policy.permissions + policyConfiguration.data.defaultFullAccessPermissionKey
            val user = request.getAttribute("user") as UserModel
            val userPermissions = user.role.permissions.map { it.key }

            val hasAccess = if (policyRoles.isNotEmpty() && user.role.key in policyRoles) {
                true
            } else {
                userPermissions.any { policyPermissions.contains(it) }
            }

            if (hasAccess) {
                return true
            } else {
                throw ApiError.Forbidden("Invalid Authorization")
            }
        } else {
            return true
        }
    }
}