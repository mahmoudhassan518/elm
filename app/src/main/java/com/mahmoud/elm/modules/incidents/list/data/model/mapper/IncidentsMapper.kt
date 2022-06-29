package com.mahmoud.elm.modules.incidents.list.data.model.mapper

import com.mahmoud.elm.modules.incidents.list.data.model.IncidentsDataResponse
import com.mahmoud.elm.modules.incidents.list.domain.entity.IncidentsEntity

fun IncidentsDataResponse.toEntity() = IncidentsEntity(
    id = id,
    description = description,
    latitude = latitude,
    longitude = longitude,
    status = status
)