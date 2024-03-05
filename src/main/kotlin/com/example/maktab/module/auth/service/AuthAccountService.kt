package com.example.maktab.module.auth.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.auth.model.CreateAccountModel
import com.example.maktab.module.auth.model.ValidateAccountModel
import com.example.maktab.module.user.mapper.UserMapper
import com.example.maktab.module.user.model.CreateUserModel
import com.example.maktab.module.user.model.FilterUserModel
import com.example.maktab.module.user.model.UserModel
import com.example.maktab.module.user.service.UserService
import com.example.maktab.module.user.specification.UserSpecification
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthAccountService(
    private val userService: UserService,
    private val userMapper: UserMapper
) {
    private val bCryptPasswordEncoder = BCryptPasswordEncoder()
    private val logger = LoggerFactory.getLogger(javaClass)

    fun createAccount(createModel: CreateAccountModel): UserModel {
        if (createModel.username == null) {
            createModel.username = UUID.fromString(createModel.email ?: createModel.phone).toString()
        } else {
            userService.findOne(UserSpecification.filterByUsername(createModel.username)).ifPresent {
                throw ApiError.Conflict("Username already used")
            }
        }

        if (createModel.password != null) {
            createModel.password = this.bCryptPasswordEncoder.encode(createModel.password)
            logger.info(createModel.password)
        }

        if (createModel.email != null) {
            userService.findOne(UserSpecification.filterByEmail(createModel.email)).ifPresent {
                throw ApiError.Conflict("Email already used")
            }
        }

        if (createModel.phone != null) {
            userService.findOne(UserSpecification.filterByPhone(createModel.phone)).ifPresent {
                throw ApiError.Conflict("Phone number already used")
            }
        }

        return userService.createUser(
            CreateUserModel(
                username = createModel.username!!,
                email = createModel.email,
                phone = createModel.phone,
                password = createModel.password
            )
        )
    }

    fun validateAccount(validateModel: ValidateAccountModel): UserModel {
        val user = userService.findOne(
            UserSpecification.filter(
                FilterUserModel(
                    username = validateModel.username,
                    email = validateModel.email,
                    phone = validateModel.phone
                )
            )
        ).orElseThrow {
            throw ApiError.NotFound("Account not founded")
        }

        if (validateModel.password != null) {
            val compared = if (user.password != null) {
                bCryptPasswordEncoder.matches(validateModel.password, user.password)
            } else {
                true
            }

            if (!compared) throw ApiError.Conflict("Invalid password")
        }

        return userMapper.toModel(user)
    }
}