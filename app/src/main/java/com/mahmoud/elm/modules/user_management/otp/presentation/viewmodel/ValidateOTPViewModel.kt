package com.mahmoud.elm.modules.user_management.otp.presentation.viewmodel

import com.mahmoud.elm.base.BaseViewModel
import com.mahmoud.elm.core.di.MainDispatcher
import com.mahmoud.elm.core.exception.EmptyFieldsException
import com.mahmoud.elm.core.exception.InvalidOTPException
import com.mahmoud.elm.modules.core.presentation.model.UIError
import com.mahmoud.elm.modules.user_management.otp.domain.entity.ValidateOTPParam
import com.mahmoud.elm.modules.user_management.otp.domain.interactor.ValidateOTPUseCase
import com.mahmoud.elm.modules.user_management.otp.presentation.model.ValidateOTPEffects
import com.mahmoud.elm.modules.user_management.otp.presentation.model.ValidateOTPEvents
import com.mahmoud.elm.modules.user_management.otp.presentation.model.ValidateOTPUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ValidateOTPViewModel @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val validateOTPUseCase: ValidateOTPUseCase
) : BaseViewModel<ValidateOTPUIState, ValidateOTPEffects, ValidateOTPEvents>(
    mainDispatcher,
    ValidateOTPUIState()
) {

    override fun transform(event: ValidateOTPEvents) {
        when (event) {
            is ValidateOTPEvents.ValidateOTP -> validateOTP(event.email, event.otp)
        }
    }

    private fun validateOTP(email: String, otp: String) =
        launchBlock(onStart = { onValidateOTPStart() }, onError = {
            it.handleErrorEffect()
        }) {
            validateOTPUseCase.invoke(ValidateOTPParam(email = email, otp = otp))
            doOnValidateOTPSuccess()
        }


    private fun onValidateOTPStart() =
        setState { copy(showLoading = true) }

    private fun doOnValidateOTPSuccess() =
        setEffect { ValidateOTPEffects.NavigateToHomeScreen }

    private fun Throwable.handleErrorEffect() {
        setEffect { ValidateOTPEffects.ShowResourceError(handleError()) }
        setState { copy(showLoading = false) }
    }

    private fun Throwable.handleError(): UIError =
        when (this) {
            is EmptyFieldsException -> UIError.hasBlankFieldsError()
            is InvalidOTPException -> UIError.getInvalidOTPError()
            else -> UIError.getUnexpectedError()
        }
}
