package com.example.maktab.module.user.mapper

import com.example.maktab.module.user.dto.UserDTO
import com.example.maktab.module.user.entity.UserEntity
import com.example.maktab.module.user.model.CreateUserModel
import com.example.maktab.module.user.model.UserModel
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {
    fun toDto(entity: UserEntity): UserDTO
    fun toEntity(dto: UserDTO): UserEntity
    fun toEntity(model: UserModel): UserEntity
    fun toModel(entity: UserEntity): UserModel
    fun toDtos(entities: List<UserEntity>): List<UserDTO>
    fun toEntities(dtos: List<UserDTO>): List<UserEntity>
    fun fromModelsToEntities(models: List<UserModel>): List<UserEntity>
    fun toModels(entities: List<UserEntity>): List<UserModel>

    fun fromCreateModelToEntity(createModel: CreateUserModel): UserEntity
}