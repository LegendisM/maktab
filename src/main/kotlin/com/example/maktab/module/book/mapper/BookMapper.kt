package com.example.maktab.module.book.mapper

import org.mapstruct.Mapper
import com.example.maktab.module.book.dto.BookDto
import com.example.maktab.module.book.dto.CreateBookRequestDto
import com.example.maktab.module.book.entity.BookEntity
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

@Mapper
interface BookMapper {
    fun toDto(entity: BookEntity): BookDto
    fun toEntity(dto: BookDto): BookEntity
    fun toDto(entities: List<BookEntity>): List<BookDto>
    fun toEntity(dtos: List<BookDto>): List<BookEntity>

    @Mapping(target = "id", constant = "EMPTY")
    fun fromCreateDtoToEntity(createDto: CreateBookRequestDto): BookEntity
}