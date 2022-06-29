package com.mahmoud.elm.modules.incidents.list.presentation.model

import java.util.*

data class IncidentListUIState(
    val showLoading: Boolean = false,
    val errorMessage: Int? = null,
    val emptyMessage: Int? = null,
    val incidents: MutableList<IncidentsDataUIState> = mutableListOf(),
    val startDateFilter: String? = null,
    val currentStatusFilter: StatusTypeEnum? = null,
    var cal: Calendar = Calendar.getInstance()
)


data class IncidentsDataUIState(
    val id: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val status: StatusTypeEnum?
)