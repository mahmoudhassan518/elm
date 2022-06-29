package com.mahmoud.elm.modules.user_management.core.domain

import com.mahmoud.elm.modules.user_management.otp.domain.entity.ValidateOTPParam
import com.mahmoud.elm.modules.user_management.sign_in.domain.entity.param.SignInParam
import com.mahmoud.elm.modules.user_management.user.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserManagementRepository {

    suspend fun signIn(param: SignInParam)
    fun validateOTP(param: ValidateOTPParam): Flow<UserEntity>
}