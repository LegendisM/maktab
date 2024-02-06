package com.example.maktab.module.auth.service

import com.example.maktab.module.auth.dto.SigninCredentialRequestDTO
import com.example.maktab.module.auth.dto.SignupCredentialRequestDTO
import com.example.maktab.module.auth.model.CreateAccountModel
import org.springframework.stereotype.Service

@Service
class AuthCredentialService(
    val authAccountService: AuthAccountService,
    val authTokenService: AuthTokenService
) {
    fun signup(signupDto: SignupCredentialRequestDTO) {
        val user = authAccountService.createAccount(
            CreateAccountModel(
                username = signupDto.username,
                email = signupDto.email,
                password = signupDto.password
            )
        )

        authTokenService.generateTokens()
    }

    fun signin(signinDto: SigninCredentialRequestDTO) {

    }
}