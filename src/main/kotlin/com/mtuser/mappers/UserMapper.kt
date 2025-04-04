package com.mtuser.mappers

import com.mtuser.dtos.UserDto
import com.mtuser.domain.users.UserModel

fun UserDto.toUserEntity(): UserModel =
    UserModel(
        fullName = this.fullName,
        password = this.password,
        email = this.email,
        nickname = this.nickname
    )