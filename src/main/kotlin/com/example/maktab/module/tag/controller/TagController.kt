package com.example.maktab.module.tag.controller

import com.example.maktab.common.annotation.Auth
import com.example.maktab.common.annotation.Policy
import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.tag.dto.CreateTagRequestDTO
import com.example.maktab.module.tag.dto.FilterTagRequestDTO
import com.example.maktab.module.tag.dto.TagDTO
import com.example.maktab.module.tag.service.TagService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/tags")
class TagController(
    private val tagService: TagService
) {
    @PostMapping
    @Auth
    @Policy(permissions = ["tag-management"])
    fun createTag(
        @RequestBody @Valid createDto: CreateTagRequestDTO
    ): ApiDTO.Response.Success<TagDTO> {
        val result = tagService.createTag(createDto)

        return ApiDTO.Response.Success(result, status = HttpStatus.CREATED)
    }

    @PostMapping("/filter")
    fun getAllTags(
        @RequestBody @Valid filterDto: FilterTagRequestDTO,
        page: Pageable
    ): ApiDTO.Response.Success<Page<TagDTO>> {
        val result = tagService.getAllTags(filterDto, page)

        return ApiDTO.Response.Success(result)
    }

    @GetMapping("/{key}")
    fun getTagById(@PathVariable("key") key: String): ApiDTO.Response.Success<TagDTO> {
        val result = tagService.getTagByKey(key)

        return ApiDTO.Response.Success(result)
    }

    @DeleteMapping("/{key}")
    @Auth
    @Policy(permissions = ["tag-management"])
    fun deleteTag(@PathVariable("key") @Valid key: String): ApiDTO.Response.Success<String> {
        tagService.deleteTag(key)

        return ApiDTO.Response.Success("Deleted Successfully")
    }
}