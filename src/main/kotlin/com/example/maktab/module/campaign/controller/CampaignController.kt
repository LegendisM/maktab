package com.example.maktab.module.campaign.controller

import com.example.maktab.common.annotation.Auth
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/campaigns")
class CampaignController() {
    @PostMapping
    @Auth
    fun createCampaign() {

    }

    @PostMapping("/filter")
    fun getAllCampaigns() {

    }

    @GetMapping("/{id}")
    fun getCampaignById() {

    }

    @PatchMapping("/{id}")
    @Auth
    fun updateCampaign() {

    }

    @DeleteMapping("/{id}")
    @Auth
    fun deleteCampaign() {

    }
}