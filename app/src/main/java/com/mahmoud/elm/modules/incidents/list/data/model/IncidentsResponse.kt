package com.mahmoud.elm.modules.incidents.list.data.model

data class IncidentsResponse(val incidents: List<IncidentsDataResponse>)

data class IncidentsDataResponse(
    val id: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val status: Int,
    val typeId: Int,
    val priority: String?,
    val issuerId: String,
    val assigneeId: Int?,
    val createdAt: String,
    val updatedAt: String,
)