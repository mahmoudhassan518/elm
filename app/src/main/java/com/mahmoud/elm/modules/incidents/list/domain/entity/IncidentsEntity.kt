package com.mahmoud.elm.modules.incidents.list.domain.entity

data class IncidentsEntity(
    val id: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val status: Int
)