package com.example.maktab.module.auth.service

import com.example.maktab.module.auth.dto.SigninCredentialRequestDTO
import com.example.maktab.module.auth.model.AuthToken
import com.example.maktab.module.auth.dto.SignupCredentialRequestDTO
import com.example.maktab.module.auth.enums.AuthTokenType
import com.example.maktab.module.auth.model.CreateAccountModel
import com.example.maktab.module.auth.model.ValidateAccountModel
import org.springframework.stereotype.Service

@Service
class AuthCredentialService(
    private val authAccountService: AuthAccountService,
    private val authTokenService: AuthTokenService
) {
    fun signup(signupDto: SignupCredentialRequestDTO): List<AuthToken> {
        val user = authAccountService.createAccount(
            CreateAccountModel(
                username = signupDto.username,
                email = signupDto.email,
                password = signupDto.password
            )
        )

        return authTokenService.createManyTokens(
            listOf(AuthTokenType.ACCESS, AuthTokenType.REFRESH),
            user
        )
    }

    fun signin(signinDto: SigninCredentialRequestDTO): List<AuthToken> {
        val user = authAccountService.validateAccount(
            ValidateAccountModel(
                username = signinDto.username,
                password = signinDto.password
            )
        )

        return authTokenService.createManyTokens(
            listOf(AuthTokenType.ACCESS, AuthTokenType.REFRESH),
            user
        )
    }

}