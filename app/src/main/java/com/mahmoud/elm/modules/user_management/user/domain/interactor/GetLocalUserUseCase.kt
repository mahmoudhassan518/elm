package com.mahmoud.elm.modules.user_management.user.domain.interactor

import com.mahmoud.elm.base.SuspendUseCase
import com.mahmoud.elm.modules.user_management.user.domain.entity.UserEntity
import com.mahmoud.elm.modules.user_management.user.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocalUserUseCase @Inject constructor(
    private val repository: UserRepository
) : SuspendUseCase<Unit, UserEntity> {
    override suspend fun invoke(param: Unit) =
        repository.getLocalUser()
}
