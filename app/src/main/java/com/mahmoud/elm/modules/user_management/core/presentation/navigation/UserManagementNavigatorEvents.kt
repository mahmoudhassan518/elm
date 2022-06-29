package com.mahmoud.elm.modules.user_management.core.presentation.navigation

sealed class UserManagementNavigatorEvents {

    data class OpenValidateOTPScreen(val email: String) : UserManagementNavigatorEvents()
    object OpenSignInScreen : UserManagementNavigatorEvents()
    object OpenHomeScreen : UserManagementNavigatorEvents()
//    object OnBackPressed : UserManagementNavigatorEvents()
}
