package com.mtuser.dtos

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ItemAnnouncedDto(
    val title: String,
    val globalItemId: UUID,
    val localStamp: LocalDateTime,
    val sellerEmail: String,
    val amount: BigDecimal,
    val gameBelong: String,
)
