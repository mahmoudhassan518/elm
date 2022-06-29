package com.mahmoud.elm.modules.user_management.sign_in.presentation.viewmodel

import com.mahmoud.elm.base.BaseViewModel
import com.mahmoud.elm.core.di.MainDispatcher
import com.mahmoud.elm.core.exception.EmptyFieldsException
import com.mahmoud.elm.core.exception.InvalidEmailException
import com.mahmoud.elm.core.exception.InvalidOTPException
import com.mahmoud.elm.modules.core.presentation.model.UIError
import com.mahmoud.elm.modules.user_management.sign_in.domain.entity.param.SignInParam
import com.mahmoud.elm.modules.user_management.sign_in.domain.interactor.SignInUseCase
import com.mahmoud.elm.modules.user_management.sign_in.presentation.model.SignInEffects
import com.mahmoud.elm.modules.user_management.sign_in.presentation.model.SignInEvents
import com.mahmoud.elm.modules.user_management.sign_in.presentation.model.SignInUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val signInUseCase: SignInUseCase
) : BaseViewModel<SignInUIState, SignInEffects, SignInEvents>(
    mainDispatcher,
    SignInUIState()
) {

    override fun transform(event: SignInEvents) {
        when (event) {
            is SignInEvents.SignIn -> signIn(event.email)
        }
    }

    private fun signIn(email: String) =
        launchBlock(onStart = { onSignInStart() }, onError = {
            it.handleErrorEffect()
        }) {
            signInUseCase.invoke(SignInParam(email = email))
            doOnSignInSuccess(email)
        }


    private fun onSignInStart() =
        setState { copy(showLoading = true) }

    private fun doOnSignInSuccess(email: String) {
        setEffect { SignInEffects.NavigateToValidateOTPScreen(email) }
        setState { copy(showLoading = false) }
    }

    private fun Throwable.handleErrorEffect() {
        setEffect { SignInEffects.ShowResourceError(handleError()) }
        setState { copy(showLoading = false) }
    }

    private fun Throwable.handleError(): UIError =
        when (this) {
            is EmptyFieldsException -> UIError.hasBlankFieldsError()
            is InvalidEmailException -> UIError.getInvalidEmailAddressError()
            else -> UIError.getUnexpectedError()
        }
}
