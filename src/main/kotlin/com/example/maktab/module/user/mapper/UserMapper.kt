package com.example.maktab.module.user.mapper

import com.example.maktab.module.user.dto.UserDTO
import com.example.maktab.module.user.entity.UserEntity
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {
    fun toDto(entity: UserEntity): UserDTO
    fun toEntity(dto: UserDTO): UserEntity
    fun toDtos(entities: List<UserEntity>): List<UserDTO>
    fun toEntities(dtos: List<UserDTO>): List<UserEntity>
}