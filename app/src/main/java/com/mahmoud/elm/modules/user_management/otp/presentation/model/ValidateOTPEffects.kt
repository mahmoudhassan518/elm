package com.mahmoud.elm.modules.user_management.otp.presentation.model

import com.mahmoud.elm.modules.core.presentation.model.UIError

sealed class ValidateOTPEffects {
    data class ShowResourceError(val error: UIError) : ValidateOTPEffects()
    object NavigateToHomeScreen : ValidateOTPEffects()
}
