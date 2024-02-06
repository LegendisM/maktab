package com.example.maktab.module.user.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.common.model.PaginationResponseModel
import com.example.maktab.module.user.entity.UserEntity
import com.example.maktab.module.user.mapper.UserMapper
import com.example.maktab.module.user.model.CreateUserModel
import com.example.maktab.module.user.model.FilterUserModel
import com.example.maktab.module.user.model.UserModel
import com.example.maktab.module.user.repository.UserRepository
import com.example.maktab.module.user.specification.UserSpecification
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class UserService(
    val userRepository: UserRepository,
    val userMapper: UserMapper,
) {
    private val logger = LoggerFactory.getLogger(UserService::class.simpleName)

    @Transactional
    fun createUser(createModel: CreateUserModel): UserModel {
        val user = userRepository.save(
            userMapper.fromCreateModelToEntity(createModel)
        )

        logger.info("User created with id ${user.id}")

        return userMapper.toModel(user)
    }

    @Transactional(readOnly = true)
    fun getAllUsers(filterModel: FilterUserModel, page: Pageable): PaginationResponseModel<UserModel> {
        val users = userRepository.findAll(UserSpecification.filter(filterModel), page)

        return PaginationResponseModel(
            items = userMapper.toModels(users.toList()),
            size = users.size,
            page = page.pageNumber,
            total = users.totalPages
        )
    }

    @Transactional(readOnly = true)
    fun findOne(specification: Specification<UserEntity>): Optional<UserEntity> {
        return userRepository.findOne(specification)
    }

    @Transactional(readOnly = true)
    fun getUserById(id: String): UserModel {
        val user = this.findByIdOrThrow(id)

        return userMapper.toModel(user)
    }

    @Transactional(readOnly = true)
    fun findByIdOrThrow(id: String): UserEntity {
        return userRepository.findById(id).orElseThrow { ApiError.NotFound("Invalid Id") }
    }
}