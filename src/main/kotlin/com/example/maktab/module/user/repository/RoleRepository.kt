package com.example.maktab.module.user.repository

import com.example.maktab.module.user.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<RoleEntity, String>, JpaSpecificationExecutor<RoleEntity>