package com.mtuser.dtos

import java.math.BigDecimal
import java.time.LocalDateTime

data class ItemAnnouncedDto(
    val title: String,
    val localStamp: LocalDateTime,
    val sellerEmail: String,
    val amount: BigDecimal,
    val gameBelong: String
)
