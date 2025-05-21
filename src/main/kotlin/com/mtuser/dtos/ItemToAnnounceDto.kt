package com.mtuser.dtos

import java.math.BigDecimal
import java.util.UUID

data class ItemToAnnounceDto(
    val amount: BigDecimal,
    val itemId: UUID,
    val sellerEmail: String)
