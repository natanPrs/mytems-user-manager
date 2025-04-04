package com.mtuser.repositories

import com.mtuser.domain.users.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<UserModel, UUID>{

    fun findUserByEmail(email: String): UserModel?
}