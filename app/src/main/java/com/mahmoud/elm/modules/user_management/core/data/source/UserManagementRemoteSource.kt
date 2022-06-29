package com.mahmoud.elm.modules.user_management.core.data.source

import com.mahmoud.elm.modules.core.data.source.UserManagementApiService
import com.mahmoud.elm.modules.core.di.UserManagementApiInterface
import com.mahmoud.elm.modules.user_management.otp.domain.entity.ValidateOTPParam
import com.mahmoud.elm.modules.user_management.sign_in.domain.entity.param.SignInParam
import javax.inject.Inject

class UserManagementRemoteSource @Inject constructor(@UserManagementApiInterface private val apiService: UserManagementApiService) {

    suspend fun signIn(param: SignInParam) = apiService.signIn(param)
    suspend fun validateOTP(param: ValidateOTPParam) = apiService.validateOTP(param)
}