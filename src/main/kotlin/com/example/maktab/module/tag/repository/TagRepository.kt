package com.example.maktab.module.tag.repository

import com.example.maktab.module.tag.entity.TagEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface TagRepository : JpaRepository<TagEntity, String>, JpaSpecificationExecutor<TagEntity>