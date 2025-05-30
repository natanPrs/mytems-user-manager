package com.mtuser.controllers

import com.mtuser.dtos.PurchaseDto
import com.mtuser.services.PurchaseService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/validate-users")
class PurchaseController(
    private val purchaseService: PurchaseService) {

    @PostMapping("/purchase-validate-user")
    fun validateUser(@RequestBody purchaseDto: PurchaseDto): Boolean =
        purchaseService.validateUsers(purchaseDto)
}