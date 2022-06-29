package com.mahmoud.elm.modules.user_management.user.data.repository

import com.mahmoud.elm.base.BaseRepository
import com.mahmoud.elm.core.di.IODispatcher
import com.mahmoud.elm.modules.user_management.user.data.source.UserLocalSource
import com.mahmoud.elm.modules.user_management.user.domain.entity.UserEntity
import com.mahmoud.elm.modules.user_management.user.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val localSource: UserLocalSource,
) : UserRepository, BaseRepository(ioDispatcher) {
    override suspend fun saveLocalUser(userEntity: UserEntity): UserEntity =
        runOnIO { localSource.saveUser(userEntity) }

    override suspend fun deleteLocalUser() =
        runOnIO { localSource.deleteUser() }

    override suspend fun getLocalUser(): UserEntity =
        runOnIO { localSource.getUser() }

}
