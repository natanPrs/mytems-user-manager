package com.mtuser.mappers

import com.mtuser.domain.inventory.ItemModel
import com.mtuser.dtos.ItemAnnouncedDto
import com.mtuser.dtos.ItemDto
import com.mtuser.dtos.ItemToAnnounceDto
import com.mtuser.repositories.ItemRepository
import com.mtuser.repositories.UserRepository
import java.time.LocalDateTime

fun ItemDto.toItemEntity(userRepository: UserRepository): ItemModel {
    val user = userRepository.findById(this.userOwner)
        .orElseThrow {IllegalArgumentException("User not found!")}

    return ItemModel(
        title = this.title,
        gameBelong = this.gameBelong,
        userOwner = user
    )
}

fun ItemToAnnounceDto.toItemAnnouncedDto(itemRepository: ItemRepository): ItemAnnouncedDto {
    val item = itemRepository.findById(this.itemId)
        .orElseThrow { IllegalArgumentException("Item not found!") }

    return ItemAnnouncedDto(
        title = item.title,
        localStamp = LocalDateTime.now(),
        sellerEmail = this.sellerEmail,
        amount = this.amount,
        gameBelong = item.gameBelong
    )
}
