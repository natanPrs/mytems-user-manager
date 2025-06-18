package com.mtuser.consumers

import com.mtuser.dtos.EventUpdateUserDto
import com.mtuser.services.PurchaseService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class PurchaseUpdateUserConsumer(private val purchaseService: PurchaseService) {

    @KafkaListener(topics = ["updateUser"], groupId = "user-group", containerFactory = "purchaseUpdateUsers")
    fun purchaseUpdateUserListener(eventUpdateUserDto: EventUpdateUserDto){
        purchaseService.completeTransaction(eventUpdateUserDto)
    }
}