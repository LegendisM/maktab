package com.example.maktab.common.configuration

import com.example.maktab.common.resolver.CurrentUserResolver
import com.example.maktab.common.interceptor.AuthInterceptor
import com.example.maktab.common.interceptor.PolicyInterceptor
import com.example.maktab.module.auth.service.AuthTokenService
import com.example.maktab.module.user.configuration.PolicyConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val authTokenService: AuthTokenService,
    private val policyConfiguration: PolicyConfiguration
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(
            AuthInterceptor(authTokenService)
        ).order(Ordered.HIGHEST_PRECEDENCE)

        registry.addInterceptor(
            PolicyInterceptor(policyConfiguration)
        ).order(Ordered.LOWEST_PRECEDENCE)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(CurrentUserResolver())
    }
}