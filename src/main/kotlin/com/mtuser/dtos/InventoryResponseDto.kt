package com.mtuser.dtos

import com.mtuser.domain.inventory.ItemStatus

data class InventoryResponseDto(
    val title: String,
    val status: ItemStatus
)
