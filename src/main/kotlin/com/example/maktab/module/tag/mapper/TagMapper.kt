package com.example.maktab.module.tag.mapper

import com.example.maktab.module.tag.dto.TagDTO
import com.example.maktab.module.tag.entity.TagEntity
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface TagMapper {
    fun toDto(entity: TagEntity): TagDTO
    fun toEntity(dto: TagDTO): TagEntity
    fun toDtos(entities: List<TagEntity>): List<TagDTO>
    fun toEntities(dtos: List<TagDTO>): List<TagEntity>
}