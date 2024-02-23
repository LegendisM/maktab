package com.example.maktab.module.storage.mapper

import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.example.maktab.module.storage.model.StorageResourceModel
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface StorageResourceMapper {
    fun toModel(entity: StorageResourceEntity): StorageResourceModel
    fun toEntity(dto: StorageResourceModel): StorageResourceEntity
    fun toModels(entities: List<StorageResourceEntity>): List<StorageResourceModel>
    fun toEntities(dtos: List<StorageResourceModel>): List<StorageResourceEntity>
}