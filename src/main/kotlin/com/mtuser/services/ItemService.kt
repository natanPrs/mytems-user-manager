package com.mtuser.services

import com.mtuser.domain.inventory.ItemModel
import com.mtuser.domain.inventory.ItemStatus
import com.mtuser.dtos.ItemAnnouncedDto
import com.mtuser.dtos.ItemToAnnounceDto
import com.mtuser.dtos.ItemDto
import com.mtuser.mappers.toItemAnnouncedDto
import com.mtuser.mappers.toItemEntity
import com.mtuser.repositories.ItemRepository
import com.mtuser.repositories.UserRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val userRepository: UserRepository,
    private val kafkaTemplate: KafkaTemplate<String, ItemAnnouncedDto>){

    fun createItem(itemDto: ItemDto): ItemModel {
        val newItem = itemDto.toItemEntity(userRepository)
        return itemRepository.save(newItem)
    }

    fun announceItem(itemToAnnounceDto: ItemToAnnounceDto) : ItemModel{
        val itemToAnnounce = itemRepository.findById(itemToAnnounceDto.itemId)
            .also { userRepository.findUserByEmail(itemToAnnounceDto.sellerEmail) }
            .orElseThrow { Exception("Item or user not found") }

        kafkaTemplate.send("announceItem", itemToAnnounceDto.toItemAnnouncedDto(itemRepository))

        itemToAnnounce.status = ItemStatus.ANNOUNCED

        return itemToAnnounce
    }

    fun getAllItems(): List<ItemModel> { return itemRepository.findAll() }

    fun updateItemOwner(globalItemId: UUID, newUserOwnerId: UUID){
        val newUserOwner = userRepository.findById(newUserOwnerId)
            .orElseThrow { Exception("User $newUserOwnerId not found") }

        val item = itemRepository.findByGlobalItemId(globalItemId)
            ?: throw Exception("Item $globalItemId not found")

        item.userOwner = newUserOwner
        itemRepository.save(item)
    }
}