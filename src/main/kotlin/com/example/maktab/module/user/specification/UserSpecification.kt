package com.example.maktab.module.user.specification

import com.example.maktab.module.user.entity.UserEntity
import com.example.maktab.module.user.model.FilterUserModel
import org.springframework.data.jpa.domain.Specification

object UserSpecification {
    fun filter(filterModel: FilterUserModel): Specification<UserEntity> {
        return Specification.where(
            filterByUsername(filterModel.username)
                .and(filterByEmail(filterModel.email))
                .and(filterByPhone(filterModel.phone))
        )
    }

    fun filterByUsername(username: String?) = Specification<UserEntity> { root, _, builder ->
        if (username == null) return@Specification null

        builder.like(
            builder.lower(root.get(UserEntity::username.name)),
            "%${username.lowercase()}%"
        )
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