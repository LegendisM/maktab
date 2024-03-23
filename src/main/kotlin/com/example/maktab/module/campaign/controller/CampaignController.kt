package com.example.maktab.module.campaign.controller

import com.example.maktab.common.annotation.Auth
import com.example.maktab.common.annotation.CurrentUser
import com.example.maktab.common.dto.ApiDTO
import com.example.maktab.module.campaign.dto.CampaignDTO
import com.example.maktab.module.campaign.dto.CreateCampaignRequestDTO
import com.example.maktab.module.campaign.dto.FilterCampaignRequestDTO
import com.example.maktab.module.campaign.dto.UpdateCampaignRequestDTO
import com.example.maktab.module.campaign.dto.member.CampaignMemberDTO
import com.example.maktab.module.campaign.dto.member.UpdateCampaignMemberRequestDTO
import com.example.maktab.module.campaign.service.CampaignMemberService
import com.example.maktab.module.campaign.service.CampaignService
import com.example.maktab.module.user.model.UserModel
import jakarta.validation.Valid
import org.hibernate.validator.constraints.UUID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/campaigns")
class CampaignController(
    private val campaignService: CampaignService,
    private val campaignMemberService: CampaignMemberService
) {
    @PostMapping
    @Auth
    fun createCampaign(
        @RequestBody @Valid createDto: CreateCampaignRequestDTO,
        @CurrentUser user: UserModel
    ): ApiDTO.Response.Success<CampaignDTO> {
        val result = campaignService.createCampaign(createDto, user.id)

        return ApiDTO.Response.Success(result, status = HttpStatus.CREATED)
    }

    @PostMapping("/filter")
    fun getAllCampaigns(
        @RequestBody @Valid filterDto: FilterCampaignRequestDTO,
        @PageableDefault page: Pageable
    ): ApiDTO.Response.Success<Page<CampaignDTO>> {
        val result = campaignService.getAllCampaigns(filterDto, page)

        return ApiDTO.Response.Success(result)
    }

    @GetMapping("/{id}")
    fun getCampaignById(@PathVariable("id") @Valid @UUID id: String): ApiDTO.Response.Success<CampaignDTO> {
        val result = campaignService.getCampaignById(id)

        return ApiDTO.Response.Success(result)
    }

    @PatchMapping("/{id}")
    @Auth
    fun updateCampaign(
        @PathVariable("id") @Valid @UUID id: String,
        @RequestBody @Valid updateDto: UpdateCampaignRequestDTO,
        @CurrentUser user: UserModel
    ): ApiDTO.Response.Success<CampaignDTO> {
        val result = campaignService.updateCampaign(id, updateDto, requestUserId = user.id)

        return ApiDTO.Response.Success(result)
    }

    @DeleteMapping("/{id}")
    @Auth
    fun deleteCampaign(
        @PathVariable("id") @Valid @UUID id: String,
        @CurrentUser user: UserModel
    ): ApiDTO.Response.Success<String> {
        campaignService.deleteCampaign(id, requestUserId = user.id)

        return ApiDTO.Response.Success("Deleted Successfully")
    }

    @GetMapping("/{id}/join")
    @Auth
    fun joinIntoCampaign(
        @PathVariable("id") @Valid @UUID id: String,
        @CurrentUser user: UserModel
    ): ApiDTO.Response.Success<String> {
        campaignService.joinIntoCampaign(id, user.id)

        return ApiDTO.Response.Success("You joined successfully")
    }

    @GetMapping("/{id}/leave")
    @Auth
    fun leaveFromCampaign(
        @PathVariable("id") @Valid @UUID id: String,
        @CurrentUser user: UserModel
    ): ApiDTO.Response.Success<String> {
        campaignService.leaveFromCampaign(id, user.id)

        return ApiDTO.Response.Success("You leaved successfully")
    }

    @GetMapping("/{id}/members")
    @Auth
    fun getAllMembersOfCampaign(
        @PathVariable("id") @Valid @UUID id: String,
        @PageableDefault page: Pageable,
        @CurrentUser user: UserModel
    ): ApiDTO.Response.Success<Page<CampaignMemberDTO>> {
        val result = campaignMemberService.getAllCampaignMembers(id, page, requestUserId = user.id)

        return ApiDTO.Response.Success(result)
    }

    @PatchMapping("/{id}/members/{memberId}")
    @Auth
    fun updateCampaignMember(
        @PathVariable("id") @Valid @UUID id: String,
        @PathVariable("memberId") @Valid @UUID memberId: String,
        @RequestBody @Valid updateDto: UpdateCampaignMemberRequestDTO,
        @CurrentUser user: UserModel
    ): ApiDTO.Response.Success<String> {
        campaignMemberService.updateCampaignMember(id, memberId, updateDto, requestUserId = user.id)

        return ApiDTO.Response.Success("The member of campaign updated successfully")
    }
}