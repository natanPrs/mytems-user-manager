package com.mtuser.dtos

import java.util.UUID

data class ItemDto(
    val title: String,
    val gameBelong: String,
    val userOwner: UUID
)
