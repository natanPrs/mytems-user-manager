package com.mtuser.domain.users

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class UserWallet(

    var amount: BigDecimal = BigDecimal.ZERO
){
    fun addMoney(amount: BigDecimal){
        this.amount.add(amount)
    }

    fun removeMoney(amount: BigDecimal){
        this.amount.subtract(amount)
    }
}
