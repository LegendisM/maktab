package com.example.maktab.module.storage.service

import com.example.maktab.module.storage.enums.Bucket
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CreateBucketRequest
import java.io.InputStream

@Service
class S3Service {
    private var s3Client: S3Client = S3Client.builder().region(Region.of("default")).build()

    init {
        runBlocking {
            print("Legend")
            val buckets = getAllBuckets()

            for (bucket in Bucket.entries.takeWhile { bucket -> buckets.find { bucket.name == it.name } != null }) {
                createBucket(bucket)
            }

            for (bucket in getAllBuckets()) {
                println(bucket.name ?: "NULL")
            }
        }
    }

    suspend fun createBucket(bucket: Bucket): Boolean {
        return try {
            val result = s3Client.createBucket(
                CreateBucketRequest
                    .builder()
                    .bucket(bucket.name)
                    .build()
            )
            true
        } catch (error: Exception) {
            false
        }
    }

    suspend fun getAllBuckets(): List<Bucket> {
        return s3Client.listBuckets().buckets().map {
            Bucket.valueOf(it.name())
        }
    }

    fun uploadFile(bucket: Bucket, file: InputStream) {

    }

    fun removeFile(bucket: Bucket, key: String) {

    }
}