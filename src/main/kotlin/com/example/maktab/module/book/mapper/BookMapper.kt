package com.example.maktab.module.book.mapper

import org.mapstruct.Mapper
import com.example.maktab.module.book.dto.BookDto
import com.example.maktab.module.book.entity.BookEntity
@Mapper
interface BookMapper {
    fun toDto(entity: BookEntity): BookDto
    fun toEntity(dto: BookDto): BookEntity
    fun toDto(entities: List<BookEntity>): List<BookDto>
    fun toEntity(dtos: List<BookDto>): List<BookEntity>
}