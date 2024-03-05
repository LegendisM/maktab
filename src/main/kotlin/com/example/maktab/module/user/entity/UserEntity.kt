package com.example.maktab.module.user.entity

import com.example.maktab.common.entity.BaseEntity
import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    username: String,
    email: String?,
    phone: String?,
    password: String?,
    role: RoleEntity,
    avatar: StorageResourceEntity?
) : BaseEntity() {
    @Column(unique = true, nullable = false)
    var username: String = username

    @Column
    var email: String? = email

    @Column
    var phone: String? = phone

    @Column
    @JsonIgnore
    var password: String? = password

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "key")
    var role: RoleEntity = role

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "avatar_id")
    var avatar: StorageResourceEntity? = avatar

    @Version
    val version: Long = 0L
}