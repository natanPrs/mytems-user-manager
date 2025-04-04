package com.mtuser.controllers

import com.mtuser.domain.inventory.ItemModel
import com.mtuser.dtos.ItemDto
import com.mtuser.services.ItemService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/items")
class ItemController(private val itemService: ItemService) {

    @PostMapping
    fun createItem(@RequestBody itemDto: ItemDto): ResponseEntity<ItemModel> {
        val newItem = itemService.createItem(itemDto)
        return ResponseEntity(newItem, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllItems(): ResponseEntity<List<ItemModel>> { return ResponseEntity(itemService.getAllItems(), HttpStatus.OK) }
}