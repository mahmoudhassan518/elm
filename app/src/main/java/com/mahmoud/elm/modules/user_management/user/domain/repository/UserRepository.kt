package com.mahmoud.elm.modules.user_management.user.domain.repository

import com.mahmoud.elm.modules.user_management.user.domain.entity.UserEntity

interface UserRepository {
    suspend fun saveLocalUser(userEntity: UserEntity): UserEntity
    suspend fun deleteLocalUser()
    suspend fun getLocalUser(): UserEntity

}
