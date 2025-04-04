package com.mtuser.domain.inventory

import com.mtuser.domain.users.UserModel
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "tb_items")
data class ItemModel(
    @Id
    val id: UUID = UUID.randomUUID(),

    val title: String,

    val gameBelong: String,

    val status: ItemStatus = ItemStatus.INVENTORY,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val userOwner: UserModel
)
