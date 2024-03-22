package com.example.maktab.module.user.specification

import com.example.maktab.module.user.entity.UserEntity
import com.example.maktab.module.user.model.FilterUserModel
import org.springframework.data.jpa.domain.Specification

object UserSpecification {
    fun filter(filterModel: FilterUserModel, useLikeExpression: Boolean = false): Specification<UserEntity> {
        return Specification.where(
            filterByUsername(filterModel.username, useLikeExpression = useLikeExpression)
                .and(filterByEmail(filterModel.email))
                .and(filterByPhone(filterModel.phone))
        )
    }

    fun filterByUsername(username: String?, useLikeExpression: Boolean = false): Specification<UserEntity> {
        return Specification<UserEntity> { root, _, builder ->
            if (username == null) return@Specification null

            if (useLikeExpression) {
                builder.like(
                    builder.lower(root.get(UserEntity::username.name)),
                    "%${username.lowercase()}%"
                )
            } else {
                builder.equal(root.get<String>(UserEntity::username.name), username)
            }
        }
    }

    fun filterByEmail(email: String?) = Specification<UserEntity> { root, _, builder ->
        if (email == null) return@Specification null

        builder.equal(root.get<String>(UserEntity::email.name), email)
    }

    fun filterByPhone(phone: String?) = Specification<UserEntity> { root, _, builder ->
        if (phone == null) return@Specification null

        builder.equal(root.get<String>(UserEntity::phone.name), phone)
    }
}