package com.mahmoud.elm.modules.incidents.core.di

import android.content.Context
import com.mahmoud.elm.modules.core.presentation.navigation.NavigationCoordinator
import com.mahmoud.elm.modules.incidents.core.presentation.navigation.IncidentsFlowNavigator
import com.mahmoud.elm.modules.incidents.core.presentation.navigation.IncidentsNavigationCoordinator
import com.mahmoud.elm.modules.incidents.core.presentation.navigation.IncidentsNavigatorEvents
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class IncidentsNavigationModule {

    @Provides
    @ActivityScoped
    fun provideIncidentsNavigationCoordinator(
        @ActivityContext context: Context,
        navigator: IncidentsFlowNavigator,
    ): NavigationCoordinator<IncidentsNavigatorEvents> =
        IncidentsNavigationCoordinator(
            context= context,
            navigator = navigator,
        )
}
