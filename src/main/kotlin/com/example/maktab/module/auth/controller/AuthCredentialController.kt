package com.example.maktab.module.auth.controller

import com.example.maktab.module.auth.dto.SigninCredentialRequestDTO
import com.example.maktab.module.auth.dto.SignupCredentialRequestDTO
import com.example.maktab.module.auth.enum.AuthTokenType
import com.example.maktab.module.auth.service.AuthTokenService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth/credential")
class AuthCredentialController(
    val authTokenService: AuthTokenService
) {
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid signupDto: SignupCredentialRequestDTO): Any {
        return authTokenService.createToken(AuthTokenType.ACCESS)
    }

    @PostMapping("/signin")
    fun signin(@RequestBody @Valid signinDto: SigninCredentialRequestDTO): Any {
        return authTokenService.createToken(AuthTokenType.REFRESH)
    }
}