package com.mtuser.services

import com.mtuser.dtos.EventUpdateUserDto
import com.mtuser.dtos.PurchaseDto
import com.mtuser.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PurchaseService(
    private val userRepository: UserRepository,
    private val userService: UserService,
    private val itemService: ItemService) {

    fun validateUsers(purchaseDto: PurchaseDto): UUID?{
        val sellerId = userRepository.findById(purchaseDto.sellerId)
            .orElseThrow { Exception("user not found") }.id
        userRepository.existsById(purchaseDto.buyerId)

        return sellerId
    }

    fun completeTransaction(eventUpdateUserDto: EventUpdateUserDto){
        userService.updateUsersBalance(eventUpdateUserDto.globalItemId, eventUpdateUserDto.buyerId, eventUpdateUserDto.amount)
        itemService.updateItemOwner(eventUpdateUserDto.globalItemId, eventUpdateUserDto.buyerId)

        println("Aeeeeww!!")
    }

}