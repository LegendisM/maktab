package com.example.maktab.module.auth.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.auth.model.CreateAccountModel
import com.example.maktab.module.user.model.CreateUserModel
import com.example.maktab.module.user.model.UserModel
import com.example.maktab.module.user.service.UserService
import com.example.maktab.module.user.specification.UserSpecification
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthAccountService(
    val userService: UserService
) {
    fun createAccount(createModel: CreateAccountModel): UserModel {
        if (createModel.username == null) {
            createModel.username = UUID.fromString(createModel.email ?: createModel.phone).toString()
        } else {
            userService.findOne(UserSpecification.filterByUsername(createModel.username)).orElseThrow {
                throw ApiError.Conflict("Username already used")
            }
        }

        if (createModel.password != null) {
            createModel.password = createModel.password // TODO: encrypt
        }

        if (createModel.email != null) {
            userService.findOne(UserSpecification.filterByEmail(createModel.email)).orElseThrow {
                throw ApiError.Conflict("Email already used")
            }
        }

        if (createModel.phone != null) {
            userService.findOne(UserSpecification.filterByPhone(createModel.phone)).orElseThrow {
                throw ApiError.Conflict("Phone Number already used")
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

    fun validateAccount() {

    }
}