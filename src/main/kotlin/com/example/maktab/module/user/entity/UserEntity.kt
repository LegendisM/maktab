package com.example.maktab.module.user.entity

import com.example.maktab.common.entity.BaseEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Version

@Entity
@Table(name = "users")
class UserEntity(
    @Column(unique = true, nullable = false)
    var username: String,

    @Column
    var email: String?,

    @Column
    var phone: String?,

    @Column
    @JsonIgnore
    var password: String?,

    @Version
    val version: Long = 0L
) : BaseEntity()