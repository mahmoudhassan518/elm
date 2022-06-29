package com.mahmoud.elm.modules.user_management.core.di

import android.content.Context
import com.mahmoud.elm.modules.core.presentation.navigation.NavigationCoordinator
import com.mahmoud.elm.modules.user_management.core.presentation.navigation.UserManagementFlowNavigator
import com.mahmoud.elm.modules.user_management.core.presentation.navigation.UserManagementNavigationCoordinator
import com.mahmoud.elm.modules.user_management.core.presentation.navigation.UserManagementNavigatorEvents
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class UserManagementNavigationModule {

    @Provides
    @ActivityScoped
    fun provideUserManagementNavigationCoordinator(
        @ActivityContext context: Context,
        navigator: UserManagementFlowNavigator,
    ): NavigationCoordinator<UserManagementNavigatorEvents> =
        UserManagementNavigationCoordinator(
            navigator = navigator,
            context = context
        )
}
