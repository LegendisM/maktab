package com.example.maktab.module.tag.service

import com.example.maktab.common.dto.PaginationResponseDTO
import com.example.maktab.common.exception.ApiError
import com.example.maktab.module.tag.entity.TagEntity
import com.example.maktab.module.tag.dto.CreateTagRequestDTO
import com.example.maktab.module.tag.dto.FilterTagRequestDTO
import com.example.maktab.module.tag.dto.TagDTO
import com.example.maktab.module.tag.mapper.TagMapper
import com.example.maktab.module.tag.repository.TagRepository
import com.example.maktab.module.tag.specification.TagSpecification
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TagService(
    private val tagRepository: TagRepository,
    private val tagMapper: TagMapper
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun createTag(createDto: CreateTagRequestDTO): TagDTO {
        val exists = tagRepository.exists(
            TagSpecification.filterByName(
                createDto.name
            )
        )

        if (exists) throw ApiError.Custom("Tag already exists.", status = HttpStatus.CONFLICT)

        val tag = saveTag(
            TagEntity(
                name = createDto.name
            )
        )

        logger.info("Tag created with key ${tag.key}")

        return tagMapper.toDto(tag)
    }

    @Transactional(readOnly = true)
    fun getAllTags(filterDto: FilterTagRequestDTO, page: Pageable): PaginationResponseDTO<TagDTO> {
        val tags = tagRepository.findAll(TagSpecification.filter(filterDto), page)

        return PaginationResponseDTO(
            items = tagMapper.toDtos(tags.toList()),
            size = tags.size,
            page = page.pageNumber,
            total = tags.totalPages
        )
    }

    @Transactional(readOnly = true)
    fun findAllByKeys(keys: Set<String>): List<TagEntity> {
        return tagRepository.findAllById(keys)
    }

    @Transactional(readOnly = true)
    fun getTagByKey(key: String): TagDTO {
        val tag = this.findByKeyOrThrow(key)

        return tagMapper.toDto(tag)
    }

    @Transactional(readOnly = true)
    fun findByKeyOrThrow(key: String): TagEntity {
        return tagRepository.findById(key).orElseThrow { ApiError.NotFound("Invalid Key") }
    }

    @Transactional
    fun deleteTag(key: String) {
        val tag = this.findByKeyOrThrow(key)

        tagRepository.delete(tag)

        logger.info("The tag $key removed successfully")
    }

    @Transactional
    fun saveTag(tag: TagEntity): TagEntity = tagRepository.save(tag)
}