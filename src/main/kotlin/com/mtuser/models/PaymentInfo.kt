package com.mtuser.models

import jakarta.persistence.Embeddable

@Embeddable
data class PaymentInfo(

    val cardNumber: String,
    val cardExpiry: String,
    val cardCVC: String
)
