package com.mtuser.domain.users

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class UserWallet(

    var amount: BigDecimal = BigDecimal.ZERO
)
