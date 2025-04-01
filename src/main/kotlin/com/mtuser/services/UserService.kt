package com.mtuser.services

import com.mtuser.dtos.UserDto
import com.mtuser.mappers.toUserEntity
import com.mtuser.models.UserModel
import com.mtuser.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(userDto: UserDto): UserModel {
        val newUser = userDto.toUserEntity()
        return userRepository.save(newUser)
    }

    fun deleteUser(email: String) {
        val userToDelete = userRepository.findUserByEmail(email) ?: throw Exception("User Not Found")
        return userRepository.delete(userToDelete)
    }

    fun getAllUsers(): List<UserModel> { return userRepository.findAll() }
}