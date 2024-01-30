package com.example.maktab.common.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.repository.Temporal
import java.util.Date

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null

    @Column(name = "created_at")
    @CreationTimestamp
    @setparam:Temporal(TemporalType.TIMESTAMP)
    var createdAt: Date? = null

    @Column(name = "updated_at")
    @UpdateTimestamp
    @setparam:Temporal(TemporalType.TIMESTAMP)
    var updatedAt: Date? = null

    override fun toString() = "${javaClass.simpleName}#${id}"
}