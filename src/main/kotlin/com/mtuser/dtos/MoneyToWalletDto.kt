package com.mtuser.dtos

import java.math.BigDecimal

data class MoneyToWalletDto(
    val email: String,
    val amount: BigDecimal
)
