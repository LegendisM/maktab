package com.example.maktab.module.storage.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.example.maktab.module.storage.mapper.StorageResourceMapper
import com.example.maktab.module.storage.model.CreateStorageResourceModel
import com.example.maktab.module.storage.model.StorageResourceModel
import com.example.maktab.module.storage.model.StorageResourceOwnerModel
import com.example.maktab.module.storage.repository.StorageResourceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StorageResourceService(
    private val storageResourceMapper: StorageResourceMapper,
    private val storageResourceRepository: StorageResourceRepository
) {
    @Transactional
    fun createResource(
        createModel: CreateStorageResourceModel,
        owner: StorageResourceOwnerModel
    ): StorageResourceModel {
        val resource = saveStorageResource(
            StorageResourceEntity(
                key = createModel.key,
                bucket = createModel.bucket,
                contentType = createModel.contentType,
                url = createModel.url,
                title = createModel.title,
                description = createModel.description,
                isPrivate = createModel.isPrivate,
                ownerType = owner.type,
                ownerId = owner.identifier
            )
        )

        return storageResourceMapper.toModel(resource)
    }

    @Transactional(readOnly = true)
    fun findByIdOrThrow(id: String, throwMessage: String? = null): StorageResourceEntity {
        return storageResourceRepository.findById(id)
            .orElseThrow { ApiError.NotFound(throwMessage ?: "Invalid Resource Id") }
    }

    @Transactional(readOnly = true)
    fun findAllByIds(ids: List<String>): List<StorageResourceEntity> {
        return storageResourceRepository.findAllById(ids)
    }

    @Transactional
    fun saveStorageResource(storageResource: StorageResourceEntity): StorageResourceEntity {
        return storageResourceRepository.save(storageResource)
    }
}