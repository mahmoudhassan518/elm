package com.mahmoud.elm.modules.incidents.core.presentation.navigation

import android.app.Activity
import android.content.Context
import com.mahmoud.elm.modules.core.presentation.navigation.NavigationCoordinator
import com.mahmoud.elm.modules.incidents.list.presentation.view.IncidentsListActivity
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class IncidentsNavigationCoordinator @Inject constructor(
    @ActivityContext private val context: Context,
    private val navigator: IncidentsFlowNavigator,
) : NavigationCoordinator<IncidentsNavigatorEvents> {


    override fun onStart() {
        IncidentsListActivity.startActivity(context as Activity)
    }

    override fun init(param: Any?) {
    }

    override fun onEvent(event: IncidentsNavigatorEvents) {
        when (event) {
            is IncidentsNavigatorEvents.OpenSubmitIncidentScreen ->
                navigator.showOpenSubmitIncidentScreen()
        }
    }


}
