package com.mahmoud.elm.modules.core.presentation.navigation

import android.content.Context
import com.mahmoud.elm.modules.incidents.core.presentation.navigation.IncidentsNavigationCoordinator
import com.mahmoud.elm.modules.user_management.core.presentation.navigation.UserManagementNavigationCoordinator
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class RootFlowCoordinator @Inject constructor(
    @ActivityContext private val context: Context,
    private val userManagementNavigationCoordinator: UserManagementNavigationCoordinator,
    private val incidentsNavigationCoordinator: IncidentsNavigationCoordinator
) {


}
