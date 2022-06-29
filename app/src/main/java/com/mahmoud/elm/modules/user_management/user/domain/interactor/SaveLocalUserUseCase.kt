package com.mahmoud.elm.modules.user_management.user.domain.interactor

import com.mahmoud.elm.base.SuspendUseCase
import com.mahmoud.elm.modules.user_management.user.domain.entity.UserEntity
import com.mahmoud.elm.modules.user_management.user.domain.repository.UserRepository
import javax.inject.Inject

class SaveLocalUserUseCase @Inject constructor(
    private val repository: UserRepository
) : SuspendUseCase<UserEntity, UserEntity> {

    override suspend fun invoke(param: UserEntity): UserEntity =
        repository.saveLocalUser(param)
}
