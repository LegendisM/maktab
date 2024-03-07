package com.example.maktab.module.auth.controller

import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.auth.dto.SigninCredentialRequestDTO
import com.example.maktab.module.auth.dto.SignupCredentialRequestDTO
import com.example.maktab.module.auth.model.AuthToken
import com.example.maktab.module.auth.service.AuthCredentialService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth/credential")
class AuthCredentialController(
    private val authCredentialService: AuthCredentialService
) {
    @PostMapping("/signup")
    fun signup(
        @RequestBody @Valid signupDto: SignupCredentialRequestDTO
    ): ApiDTO.Response.Success<List<AuthToken>> {
        val result = authCredentialService.signup(signupDto)

        return ApiDTO.Response.Success(result, status = HttpStatus.CREATED)
    }

    @PostMapping("/signin")
    fun signin(
        @RequestBody @Valid signinDto: SigninCredentialRequestDTO
    ): ApiDTO.Response.Success<List<AuthToken>> {
        val result = authCredentialService.signin(signinDto)

        return ApiDTO.Response.Success(result)
    }
}