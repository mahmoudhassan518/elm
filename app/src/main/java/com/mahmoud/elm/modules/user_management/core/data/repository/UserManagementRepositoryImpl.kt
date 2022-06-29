package com.mahmoud.elm.modules.user_management.core.data.repository


import com.mahmoud.elm.base.BaseRepository
import com.mahmoud.elm.core.di.IODispatcher
import com.mahmoud.elm.modules.user_management.core.data.source.UserManagementRemoteSource
import com.mahmoud.elm.modules.user_management.core.domain.UserManagementRepository
import com.mahmoud.elm.modules.user_management.otp.domain.entity.ValidateOTPParam
import com.mahmoud.elm.modules.user_management.sign_in.domain.entity.param.SignInParam
import com.mahmoud.elm.modules.user_management.user.data.model.mapper.toEntity
import com.mahmoud.elm.modules.user_management.user.domain.entity.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserManagementRepositoryImpl @Inject constructor(
    @IODispatcher private val dispatcher: CoroutineDispatcher,
    private val source: UserManagementRemoteSource
) : UserManagementRepository, BaseRepository(dispatcher) {
    override suspend fun signIn(param: SignInParam) =
        runOnIO { source.signIn(param) }

    override fun validateOTP(param: ValidateOTPParam): Flow<UserEntity> =
        requestHandler {
            source.validateOTP(param).toEntity(param.email)
        }
}