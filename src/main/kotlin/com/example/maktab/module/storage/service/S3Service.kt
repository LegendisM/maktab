package com.example.maktab.module.storage.service

import com.example.maktab.module.storage.enums.Bucket
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.identity.spi.AwsCredentialsIdentity
import software.amazon.awssdk.identity.spi.IdentityProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CreateBucketRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.InputStream
import java.net.URI

@Service
class S3Service {
    private var s3Client: S3Client = S3Client.builder()
        .credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create("", "")
            )
        )
        .endpointOverride(URI.create("https://s3.ir-thr-at1.arvanstorage.ir"))
        .region(Region.AWS_GLOBAL)
        .build()

    init {
        runBlocking {
            print("Legend")

            s3Client.putObject(
                PutObjectRequest.builder().bucket(Bucket.MAKTAB_PUBLIC.value).key("test").build(),
                RequestBody.empty()
            )
        }
    }

    fun uploadFile(bucket: Bucket, file: InputStream) {

    }

    fun removeFile(bucket: Bucket, key: String) {

    }
}