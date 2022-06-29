package com.mahmoud.elm.modules.user_management.sign_in.presentation.model

import com.mahmoud.elm.modules.core.presentation.model.UIError

sealed class SignInEffects {
    data class ShowResourceError(val error: UIError) : SignInEffects()
    data class NavigateToValidateOTPScreen(val email: String) : SignInEffects()
}
