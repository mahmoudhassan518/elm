package com.mahmoud.elm.modules.incidents.list.presentation.model

import java.util.Calendar

sealed class IncidentListEffects {
    data class ShowCalender(val cal: Calendar) : IncidentListEffects()
}
