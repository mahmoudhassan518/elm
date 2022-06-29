package com.mahmoud.elm.modules.user_management.user.domain.interactor

import com.mahmoud.elm.base.SuspendUseCase
import com.mahmoud.elm.modules.user_management.user.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val repository: UserRepository) :
    SuspendUseCase<Unit, Unit> {
    override suspend fun invoke(param: Unit) =
        repository.deleteLocalUser()
}
