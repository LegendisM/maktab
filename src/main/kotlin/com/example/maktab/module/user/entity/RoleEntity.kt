package com.example.maktab.module.user.entity

import com.example.maktab.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "roles")
class RoleEntity(
    key: String,
    name: String,
    permissions: MutableList<PermissionEntity>
) {
    @Id
    var key: String = key

    @Column
    var name: String = name

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permissions",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "key")],
        inverseJoinColumns = [JoinColumn(name = "permission_id", referencedColumnName = "key")]
    )
    var permissions: MutableList<PermissionEntity> = permissions
}