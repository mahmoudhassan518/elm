package com.mahmoud.elm.modules.user_management.otp.presentation.model

sealed class ValidateOTPEvents {
    data class ValidateOTP(val email : String, val otp: String) : ValidateOTPEvents()
}
