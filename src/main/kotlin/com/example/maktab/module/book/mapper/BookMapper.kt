package com.example.maktab.module.book.mapper

import org.mapstruct.Mapper
import com.example.maktab.module.book.dto.BookDTO
import com.example.maktab.module.book.entity.BookEntity
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BookMapper {
    fun toDto(entity: BookEntity): BookDTO
    fun toEntity(dto: BookDTO): BookEntity
    fun toDtos(entities: List<BookEntity>): List<BookDTO>
    fun toEntities(dtos: List<BookDTO>): List<BookEntity>
}