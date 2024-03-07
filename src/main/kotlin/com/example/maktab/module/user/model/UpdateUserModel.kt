package com.example.maktab.module.user.model

import com.example.maktab.module.storage.entity.StorageResourceEntity
import java.util.Optional

data class UpdateUserModel(
    var username: Optional<String?>,
    var email: Optional<String>,
    var phone: Optional<String>,
    var password: Optional<String>,
    var avatar: Optional<StorageResourceEntity>
)