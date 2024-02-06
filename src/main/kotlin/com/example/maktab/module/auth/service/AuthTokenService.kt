package com.example.maktab.module.auth.service

import com.example.maktab.common.config.SecretConfiguration
import com.example.maktab.module.auth.dto.AuthTokenDTO
import com.example.maktab.module.auth.enum.AuthTokenType
import org.springframework.stereotype.Service

@Service
class AuthTokenService(
    private val secretConfiguration: SecretConfiguration
) {
    fun createToken(type: AuthTokenType): AuthTokenDTO {
        // TODO
        return AuthTokenDTO(
            type = AuthTokenType.ACCESS,
            value = secretConfiguration.jwt.accessToken.key
        )
    }

    fun createAllTokens(types: List<AuthTokenType>): List<AuthTokenDTO> {
        // TODO
        return emptyList()
    }

    fun validateToken() {
        // TODO
    }
}