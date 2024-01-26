package com.example.maktab.module.book.mapper

import org.mapstruct.Mapper
import com.example.maktab.module.book.dto.BookDTO
import com.example.maktab.module.book.entity.BookEntity

@Mapper
interface BookMapper {
    fun toDto(entity: BookEntity): BookDTO
    fun toEntity(dto: BookDTO): BookEntity
    fun toDto(entities: List<BookEntity>): List<BookDTO>
    fun toEntity(dtos: List<BookDTO>): List<BookEntity>
}