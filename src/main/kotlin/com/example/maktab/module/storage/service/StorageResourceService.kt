package com.example.maktab.module.storage.service

import com.example.maktab.module.storage.entity.StorageResourceEntity
import com.example.maktab.module.storage.enums.StorageResourceOwner
import com.example.maktab.module.storage.mapper.StorageResourceMapper
import com.example.maktab.module.storage.model.CreateStorageResourceModel
import com.example.maktab.module.storage.model.StorageResourceModel
import com.example.maktab.module.storage.model.StorageResourceOwnerModel
import com.example.maktab.module.storage.repository.StorageResourceRepository
import com.example.maktab.module.user.model.UserModel
import org.springframework.stereotype.Service

@Service
class StorageResourceService(
    private val storageResourceMapper: StorageResourceMapper,
    private val storageResourceRepository: StorageResourceRepository
) {
    fun createResource(
        createModel: CreateStorageResourceModel,
        owner: StorageResourceOwnerModel
    ): StorageResourceModel {
        val resource = storageResourceRepository.save(
            StorageResourceEntity(
                key = createModel.key,
                bucket = createModel.bucket,
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
}