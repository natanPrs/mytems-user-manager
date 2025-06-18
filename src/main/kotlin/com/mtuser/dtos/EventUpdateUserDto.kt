package com.mtuser.dtos

import java.math.BigDecimal
import java.util.UUID

data class EventUpdateUserDto(
    val amount: BigDecimal,
    val buyerId: UUID,
    val globalItemId: UUID,
)
