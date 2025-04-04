package com.mtuser.repositories

import com.mtuser.domain.inventory.ItemModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ItemRepository : JpaRepository<ItemModel, UUID> {
}