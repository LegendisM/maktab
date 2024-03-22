package com.example.maktab.module.storage.repository

import com.example.maktab.module.storage.entity.StorageResourceEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface StorageResourceRepository : JpaRepository<StorageResourceEntity, String>, JpaSpecificationExecutor<StorageResourceEntity>