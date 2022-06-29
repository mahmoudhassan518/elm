package com.mahmoud.elm.modules.incidents.core.presentation.navigation

import android.app.Activity
import android.content.Context
import com.mahmoud.elm.modules.incidents.submit.presentation.view.SubmitIncidentActivity
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class IncidentsFlowNavigator @Inject constructor(@ActivityContext private val context: Context) {


    fun showOpenSubmitIncidentScreen() =
        SubmitIncidentActivity.startActivity(context as Activity)


}
