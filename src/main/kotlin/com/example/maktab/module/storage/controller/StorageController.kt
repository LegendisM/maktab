package com.example.maktab.module.storage.controller

import com.example.maktab.common.annotation.Auth
import com.example.maktab.common.annotation.CurrentUser
import com.example.maktab.common.annotation.ValidMultipartFile
import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.storage.enums.StorageBucket
import com.example.maktab.module.storage.model.StorageResourceModel
import com.example.maktab.module.storage.service.StorageService
import com.example.maktab.module.user.model.UserModel
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/v1/storage")
@Auth
class StorageController(
    private val storageService: StorageService
) {
    @PostMapping("/upload")
    fun uploadResource(
        @RequestParam("file") @ValidMultipartFile file: MultipartFile,
        @CurrentUser user: UserModel
    ): ApiDTO.Response.Success<StorageResourceModel> {
        val result = storageService.uploadResource(StorageBucket.MAKTAB_PUBLIC, file, user)

        return ApiDTO.Response.Success(result)
    }
}