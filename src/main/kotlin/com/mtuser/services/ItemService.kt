package com.mtuser.services

import com.mtuser.domain.inventory.ItemModel
import com.mtuser.dtos.ItemDto
import com.mtuser.mappers.toItemEntity
import com.mtuser.repositories.ItemRepository
import com.mtuser.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val userRepository: UserRepository) {

    fun createItem(itemDto: ItemDto): ItemModel {
        val newItem = itemDto.toItemEntity(userRepository)
        return itemRepository.save(newItem)
    }

    fun getAllItems(): List<ItemModel> { return itemRepository.findAll() }
}