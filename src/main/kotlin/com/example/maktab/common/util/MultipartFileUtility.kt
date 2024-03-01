package com.example.maktab.common.util

import com.example.maktab.common.exception.ApiError
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

fun MultipartFile.uniqueKey(): String {
    if (this.originalFilename == null){
        throw ApiError.Conflict("Invalid original file name")
    }

    return "${UUID.randomUUID().toString()}.${this.contentType?.split("/")?.get(1)}"
}