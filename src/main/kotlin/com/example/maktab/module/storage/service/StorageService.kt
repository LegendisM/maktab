package com.example.maktab.module.storage.service

import com.example.maktab.common.exception.ApiError
import com.example.maktab.common.util.uniqueKey
import com.example.maktab.module.storage.enums.StorageBucket
import com.example.maktab.module.storage.enums.StorageResourceOwner
import com.example.maktab.module.storage.model.CreateStorageResourceModel
import com.example.maktab.module.storage.model.StorageResourceModel
import com.example.maktab.module.storage.model.StorageResourceOwnerModel
import com.example.maktab.module.user.model.UserModel
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.services.s3.model.ObjectCannedACL

@Service
class StorageService(
    private val s3Service: S3Service,
    private val storageResourceService: StorageResourceService
) {
    fun uploadResource(bucket: StorageBucket, file: MultipartFile, owner: UserModel?): StorageResourceModel {
        val key = file.uniqueKey()
        val contentType = file.contentType!!
        val result = s3Service.putObject(bucket, key, file.inputStream)

        if (!result.sdkHttpResponse().isSuccessful) throw ApiError.Conflict("Failed to upload file")

        val objectUrl = when (bucket.acl) {
            ObjectCannedACL.PUBLIC_READ -> s3Service.generateObjectUrl(bucket, key)
            else -> null
        }

        return storageResourceService.createResource(
            CreateStorageResourceModel(
                key = key,
                bucket = bucket,
                contentType = contentType,
                url = objectUrl,
                title = "Untitled",
                description = "No description",
                isPrivate = objectUrl == null
            ),
            StorageResourceOwnerModel(
                type = owner?.let { StorageResourceOwner.USER } ?: StorageResourceOwner.SYSTEM,
                identifier = owner?.id ?: StorageResourceOwner.SYSTEM.value
            )
        )
    }
}