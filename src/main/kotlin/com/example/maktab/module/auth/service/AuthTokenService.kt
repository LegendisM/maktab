package com.example.maktab.module.auth.service

import com.example.maktab.common.config.SecretConfiguration
import com.example.maktab.module.auth.model.AuthToken
import com.example.maktab.module.auth.enums.AuthTokenType
import com.example.maktab.module.auth.model.AuthTokenPayload
import com.example.maktab.module.user.model.UserModel
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*


@Service
class AuthTokenService(
    private val secretConfiguration: SecretConfiguration
) {
    private val accessKey = Keys.hmacShaKeyFor(secretConfiguration.jwt.accessToken.key.toByteArray())
    private val refreshKey = Keys.hmacShaKeyFor(secretConfiguration.jwt.refreshToken.key.toByteArray())

    fun createToken(type: AuthTokenType, payload: UserModel): AuthToken {
        val issuedAt = Date()
        val token: String = when (type) {
            AuthTokenType.ACCESS -> {
                val expirationAt = Date(issuedAt.time + secretConfiguration.jwt.accessToken.expire)

                Jwts.builder()
                    .setSubject(payload.username)
                    .addClaims(
                        mapOf(
                            "id" to payload.id,
                            "username" to payload.username
                        )
                    )
                    .setIssuedAt(issuedAt)
                    .setExpiration(expirationAt)
                    .signWith(accessKey)
                    .compact()
            }

            AuthTokenType.REFRESH -> {
                val expirationAt = Date(issuedAt.time + secretConfiguration.jwt.refreshToken.expire)

                Jwts.builder()
                    .setSubject(payload.username)
                    .addClaims(
                        mapOf(
                            "id" to payload.id,
                            "username" to payload.username
                        )
                    )
                    .setIssuedAt(issuedAt)
                    .setExpiration(expirationAt)
                    .signWith(refreshKey)
                    .compact()
            }
        }

        return AuthToken(
            type = type,
            value = token
        )
    }

    fun createManyTokens(types: List<AuthTokenType>, payload: UserModel): List<AuthToken> {
        return types.map {
            this.createToken(it, payload)
        }
    }

    fun validateToken(token: String): AuthTokenPayload {
        val parser = Jwts.parserBuilder()
            .setSigningKey(accessKey)
            .build()

        @Suppress("UNCHECKED_CAST")
        val body = parser.parse(token).body as Map<String, String>

        return AuthTokenPayload(
            id = body["id"]!!,
            username = body["username"]!!
        )
    }
}