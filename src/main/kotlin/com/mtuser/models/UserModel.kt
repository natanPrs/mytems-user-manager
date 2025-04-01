package com.mtuser.models

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "tb_user")
data class UserModel(

    @Id
    val id: UUID = UUID.randomUUID(),

    val fullName: String,

    val password: String,

    @Column(unique = true)
    val email: String,

    @Column(unique = true)
    val nickname: String,

    @Embedded
    val paymentInfo: PaymentInfo? = null

)
