package com.mahmoud.elm.modules.user_management.core.presentation.navigation

import androidx.navigation.NavController
import com.mahmoud.elm.R
import com.mahmoud.elm.modules.user_management.sign_in.presentation.view.SignInFragmentDirections
import javax.inject.Inject

class UserManagementFlowNavigator @Inject constructor() {

    lateinit var navController: NavController

    fun showValidateOTPFragment(email: String) =
        navController.navigate(
            SignInFragmentDirections.actionSignInFragmentToValidateOTPFragment(email)
        )

    fun showSignInFragment() =
        navController.popBackStack()
}
