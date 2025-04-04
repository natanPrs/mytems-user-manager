package com.mtuser.mappers

import com.mtuser.domain.inventory.ItemModel
import com.mtuser.dtos.ItemDto
import com.mtuser.repositories.UserRepository

fun ItemDto.toItemEntity(userRepository: UserRepository): ItemModel {
    val user = userRepository.findById(this.userOwner)
        .orElseThrow {IllegalArgumentException("User not found!")}

    return ItemModel(
        title = this.title,
        gameBelong = this.gameBelong,
        userOwner = user
    )
}