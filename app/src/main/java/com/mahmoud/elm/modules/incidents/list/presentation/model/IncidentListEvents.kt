package com.mahmoud.elm.modules.incidents.list.presentation.model

import java.util.*

sealed class IncidentListEvents {
    object GetIncidentList : IncidentListEvents()
    data class GetIncidentListByDate(val date: Calendar) : IncidentListEvents()
    data class GetIncidentListByStatue(val status: StatusTypeEnum) : IncidentListEvents()
    object ShowCalenderDialog : IncidentListEvents()
}
