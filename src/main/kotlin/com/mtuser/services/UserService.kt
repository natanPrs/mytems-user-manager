package com.mtuser.services

import com.mtuser.dtos.UserDto
import com.mtuser.mappers.toUserEntity
import com.mtuser.domain.users.UserModel
import com.mtuser.dtos.InventoryResponseDto
import com.mtuser.dtos.MoneyToWalletDto
import com.mtuser.repositories.ItemRepository
import com.mtuser.repositories.UserRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val itemRepository: ItemRepository) {

    fun createUser(userDto: UserDto): UserModel {
        val newUser = userDto.toUserEntity()
        return userRepository.save(newUser)
    }

    fun deleteUser(email: String) {
        val userToDelete = userRepository.findUserByEmail(email) ?: throw Exception("User Not Found")
        return userRepository.delete(userToDelete)
    }

    fun getAllUsers(): List<UserModel> { return userRepository.findAll() }

    fun getInventory(email: String): List<InventoryResponseDto> {
        return itemRepository.findTitlesByUserEmail(email)
    }

    fun addMoneyToWallet(moneyToWalletDto: MoneyToWalletDto): UserModel {
        val user = userRepository.findUserByEmail(moneyToWalletDto.email) ?: throw Exception("User not found.")
        user.wallet.addMoney(moneyToWalletDto.amount)

        return userRepository.save(user)
    }

    fun updateUsersBalance(globalItemId: UUID, buyerId: UUID, amount: BigDecimal){
        val seller = itemRepository.findByGlobalItemId(globalItemId)
            ?.userOwner ?: throw Exception("Item $globalItemId not Found")

        val buyer = userRepository.findById(buyerId)
            .orElseThrow { Exception("User $buyerId not found.") }

        seller.wallet.addMoney(amount)
        buyer.wallet.removeMoney(amount)

        userRepository.saveAll(listOf(seller, buyer))
    }
}