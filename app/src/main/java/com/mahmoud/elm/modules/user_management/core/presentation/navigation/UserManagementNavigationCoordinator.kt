package com.mahmoud.elm.modules.user_management.core.presentation.navigation

import android.app.Activity
import android.content.Context
import androidx.navigation.NavController
import com.mahmoud.elm.modules.core.presentation.navigation.NavigationCoordinator
import com.mahmoud.elm.modules.incidents.list.presentation.view.IncidentsListActivity
import com.mahmoud.elm.modules.user_management.core.presentation.view.UserManagementActivity
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class UserManagementNavigationCoordinator @Inject constructor(
    private val navigator: UserManagementFlowNavigator,
    @ActivityContext private val context: Context,
) : NavigationCoordinator<UserManagementNavigatorEvents> {

    private lateinit var navController: NavController

    override fun onStart() {
        UserManagementActivity.startActivity(context as Activity)
    }

    override fun init(param: Any?) {
        navController = param as NavController
        navigator.navController = navController
    }

    override fun onEvent(event: UserManagementNavigatorEvents) {
        when (event) {
            is UserManagementNavigatorEvents.OpenHomeScreen ->
                IncidentsListActivity.startActivity(context as Activity) // this will be replaced later by root flow coordinator
            is UserManagementNavigatorEvents.OpenValidateOTPScreen ->
                navigator.showValidateOTPFragment(event.email)
            is UserManagementNavigatorEvents.OpenSignInScreen ->
                navigator.showSignInFragment()
        }
    }


}
