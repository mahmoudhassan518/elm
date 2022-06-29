package com.mahmoud.elm.modules.user_management.otp.domain.interactor

import com.mahmoud.elm.base.FlowUseCase
import com.mahmoud.elm.core.exception.EmptyFieldsException
import com.mahmoud.elm.core.exception.InvalidOTPException
import com.mahmoud.elm.core.extentions.isValidOTP
import com.mahmoud.elm.modules.user_management.core.domain.UserManagementRepository
import com.mahmoud.elm.modules.user_management.otp.domain.entity.ValidateOTPParam
import com.mahmoud.elm.modules.user_management.user.domain.entity.UserEntity
import com.mahmoud.elm.modules.user_management.user.domain.interactor.SaveLocalUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ValidateOTPUseCase @Inject constructor(
    private val repository: UserManagementRepository,
    private val saveLocalUserUseCase: SaveLocalUserUseCase,
) : FlowUseCase<ValidateOTPParam, UserEntity> {

    override fun invoke(param: ValidateOTPParam): Flow<UserEntity> =
        with(param.otp) {
            if (hasEmptyFields())
                throw EmptyFieldsException
            else if (!isValidOTP())
                throw InvalidOTPException
            else
                repository.validateOTP(param).flatMapMerge {
                    flowOf(saveLocalUserUseCase(it))
                }
        }

    private fun String.hasEmptyFields() =
        this.trim().isEmpty()

}