package com.example.maktab.module.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "permissions")
class PermissionEntity(
    key: String,
    name: String,
    groupKey: String
) {
    @Id
    var key: String = key

    @Column
    var name: String = name

    @Column
    var groupKey: String = groupKey
}