package com.mahmoud.elm.modules.user_management.sign_in.domain.interactor

import com.mahmoud.elm.core.exception.EmptyFieldsException
import com.mahmoud.elm.core.exception.InvalidEmailException
import com.mahmoud.elm.base.SuspendUseCase
import com.mahmoud.elm.core.extentions.isValidEmail
import com.mahmoud.elm.modules.user_management.core.domain.UserManagementRepository
import com.mahmoud.elm.modules.user_management.sign_in.domain.entity.param.SignInParam
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: UserManagementRepository,
) : SuspendUseCase<SignInParam, Unit> {

    override suspend fun invoke(param: SignInParam): Unit =
        with(param.email) {
            if (hasEmptyFields())
                throw EmptyFieldsException
            else if (!isValidEmail())
                throw InvalidEmailException
            else
                repository.signIn(param)
        }

    private fun String.hasEmptyFields() =
        this.trim().isEmpty()

}