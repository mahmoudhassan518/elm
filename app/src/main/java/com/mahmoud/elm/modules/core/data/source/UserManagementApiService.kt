package com.mahmoud.elm.modules.core.data.source

import com.mahmoud.elm.modules.user_management.otp.domain.entity.ValidateOTPParam
import com.mahmoud.elm.modules.user_management.sign_in.domain.entity.param.SignInParam
import com.mahmoud.elm.modules.user_management.user.data.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserManagementApiService {

    @POST("login")
    suspend fun signIn(@Body param: SignInParam)

    @POST("verify-otp")
    suspend fun validateOTP(@Body param: ValidateOTPParam) : UserResponse
}
