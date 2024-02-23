package com.example.maktab.module.storage.enums

import software.amazon.awssdk.services.s3.model.ObjectCannedACL

enum class StorageBucket(val key: String, val acl: ObjectCannedACL) {
    MAKTAB_PUBLIC(key = "maktab-public", acl = ObjectCannedACL.PUBLIC_READ)
}