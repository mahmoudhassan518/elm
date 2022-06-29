package com.mahmoud.elm.modules.user_management.sign_in.presentation.model

sealed class SignInEvents {
    data class SignIn(val email: String) : SignInEvents()
}
