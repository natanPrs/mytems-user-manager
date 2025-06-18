package com.mtuser.dtos

import java.util.UUID

data class PurchaseDto(
    val buyerId: UUID,
    val sellerId: UUID,
    val globalItemId: UUID,
)
