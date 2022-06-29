package com.mahmoud.elm.modules.incidents.list.presentation.model.mapper

import com.mahmoud.elm.modules.incidents.list.domain.entity.IncidentsEntity
import com.mahmoud.elm.modules.incidents.list.presentation.model.IncidentsDataUIState
import com.mahmoud.elm.modules.incidents.list.presentation.model.StatusTypeEnum

fun IncidentsEntity.toUIState() = IncidentsDataUIState(
    id = id,
    latitude = latitude,
    longitude = longitude,
    description = description,
    status = status.fromInt()


)

private fun Int.fromInt() = StatusTypeEnum.values().firstOrNull { it.value == this }
