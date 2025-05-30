package com.mtuser.services

import com.mtuser.dtos.PurchaseDto
import com.mtuser.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val userRepository: UserRepository) {

    fun validateUsers(purchaseDto: PurchaseDto): Boolean{
        return userRepository.existsById(purchaseDto.sellerId) &&
                userRepository.existsById(purchaseDto.buyerId)
    }
}