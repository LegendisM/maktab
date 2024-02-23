package com.example.maktab.module.storage.service

import com.example.maktab.common.configuration.AwsConfiguration
import com.example.maktab.module.storage.enums.StorageBucket
import org.springframework.stereotype.Service
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse
import software.amazon.awssdk.services.s3.model.GetUrlRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectResponse
import java.io.InputStream
import java.net.URI

@Service
class S3Service(
    private val awsConfiguration: AwsConfiguration
) {
    private var s3Client: S3Client = S3Client.builder()
        .credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(
                    awsConfiguration.credentials.accessKey,
                    awsConfiguration.credentials.secretKey
                )
            )
        )
        .endpointOverride(URI.create(awsConfiguration.s3.endpoint))
        .region(Region.of(awsConfiguration.s3.region))
        .build()

    fun putObject(bucket: StorageBucket, key: String, inputStream: InputStream): PutObjectResponse {
        return s3Client.putObject(
            PutObjectRequest.builder()
                .bucket(bucket.key)
                .key(key)
                .acl(bucket.acl)
                .build(),
            RequestBody.fromInputStream(inputStream, inputStream.available().toLong())
        )
    }

    fun deleteObject(bucket: StorageBucket, key: String): DeleteObjectResponse {
        return s3Client.deleteObject(
            DeleteObjectRequest.builder()
                .bucket(bucket.key)
                .key(key)
                .build()
        )
    }

    fun generateObjectUrl(bucket: StorageBucket, key: String): String {
        return s3Client.utilities().getUrl(
            GetUrlRequest.builder()
                .endpoint(URI.create(awsConfiguration.s3.endpoint))
                .region(Region.of(awsConfiguration.s3.region))
                .bucket(bucket.key)
                .key(key)
                .build()
        ).toURI().toString()
    }
}