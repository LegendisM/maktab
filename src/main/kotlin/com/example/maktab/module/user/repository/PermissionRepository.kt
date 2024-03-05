package com.example.maktab.module.user.repository

import com.example.maktab.module.user.entity.PermissionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PermissionRepository : JpaRepository<PermissionEntity, String>, JpaSpecificationExecutor<PermissionEntity>