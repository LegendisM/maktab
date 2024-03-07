package com.example.maktab.module.user.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.common.model.PaginationResponseModel
import com.example.maktab.module.storage.service.StorageResourceService
import com.example.maktab.module.user.entity.UserEntity
import com.example.maktab.module.user.mapper.UserMapper
import com.example.maktab.module.user.model.CreateUserModel
import com.example.maktab.module.user.model.FilterUserModel
import com.example.maktab.module.user.model.UpdateUserModel
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
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val roleService: RoleService,
    private val storageResourceService: StorageResourceService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun createUser(createModel: CreateUserModel): UserModel {
        val defaultRole = roleService.getDefaultUserRole()
        val user = userRepository.save(createModel.let {
            UserEntity(
                username = it.username,
                email = it.email,
                phone = it.phone,
                password = it.password,
                role = defaultRole,
                avatar = null
            )
        })

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
    fun getUserById(id: String): UserModel {
        val user = this.findByIdOrThrow(id)

        return userMapper.toModel(user)
    }

    @Transactional(readOnly = true)
    fun findOne(specification: Specification<UserEntity>): Optional<UserEntity> {
        return userRepository.findOne(specification)
    }

    @Transactional(readOnly = true)
    fun findByIdOrThrow(id: String): UserEntity {
        return userRepository.findById(id).orElseThrow { ApiError.NotFound("Invalid Id") }
    }

    @Transactional(readOnly = true)
    fun existBy(username: String): Boolean {
        return userRepository.exists(UserSpecification.filterByUsername(username))
    }

    @Transactional
    fun updateUser(id: String, updateModel: UpdateUserModel): UserModel {
        val user = this.findByIdOrThrow(id)

//        user.apply {
//            updateModel.username.ifPresent {
//                this.username = it
//            } // TODO: fix here to when the optional is exist, update the value of field (null or filled) to target entity field
//
//            updateModel.username.let {
//                if ((this@UserService.existBy(it))) throw ApiError.Conflict("Username already used")
//                this.username = it
//            }
//            updateModel.email?.let { this.email = it }
//            updateModel.phone?.let { this.phone = it }
//            updateModel.password?.let { this.password = it }
//            updateModel.avatar?.let { this.avatar = it }
//        }

        userRepository.save(user)

        logger.info("The user $id updated successfully")

        return userMapper.toModel(user)
    }
}