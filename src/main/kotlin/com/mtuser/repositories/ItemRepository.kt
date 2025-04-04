package com.mtuser.repositories

import com.mtuser.domain.inventory.ItemModel
import com.mtuser.dtos.InventoryResponseDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface ItemRepository : JpaRepository<ItemModel, UUID> {

    @Query("SELECT NEW com.mtuser.dtos.InventoryResponseDto(i.title, i.status) FROM ItemModel i WHERE i.userOwner.email = :email")
    fun findTitlesByUserEmail(@Param("email") email: String): List<InventoryResponseDto>
}