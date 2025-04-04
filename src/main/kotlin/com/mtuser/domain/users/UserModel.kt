package com.mtuser.domain.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.mtuser.domain.inventory.ItemModel
import jakarta.persistence.*
import java.util.*

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
        val wallet: UserWallet? = null,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @OneToMany(mappedBy = "userOwner", fetch = FetchType.LAZY)
        val inventory: MutableSet<ItemModel> = mutableSetOf()

    )
