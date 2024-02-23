package com.example.maktab.common.configuration

import com.example.maktab.common.resolver.CurrentUserResolver
import com.example.maktab.common.interceptor.AuthInterceptor
import com.example.maktab.module.auth.service.AuthTokenService
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    val authTokenService: AuthTokenService
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(
            AuthInterceptor(authTokenService)
        )
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(CurrentUserResolver())
    }
}