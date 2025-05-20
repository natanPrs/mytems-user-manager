package com.mtuser.services

import com.mtuser.domain.inventory.ItemModel
import com.mtuser.domain.inventory.ItemStatus
import com.mtuser.dtos.AnnounceItemDto
import com.mtuser.dtos.ItemDto
import com.mtuser.mappers.toItemEntity
import com.mtuser.repositories.ItemRepository
import com.mtuser.repositories.UserRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import javax.print.PrintService

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val userRepository: UserRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>) {

    fun createItem(itemDto: ItemDto): ItemModel {
        val newItem = itemDto.toItemEntity(userRepository)
        return itemRepository.save(newItem)
    }

    fun announceItem(announceItemDto: AnnounceItemDto) : ItemModel{
        val itemToAnnounce = itemRepository.findById(announceItemDto.itemId)
            .orElseThrow { Exception("Item not found") }

        kafkaTemplate.send("announceItem", "Ola, estou testando o meu kafka!")

        itemToAnnounce.status = ItemStatus.ANNOUNCED

        return itemToAnnounce
    }

    fun getAllItems(): List<ItemModel> { return itemRepository.findAll() }
}