package com.mahmoud.elm.modules.incidents.list.domain.repository

import com.mahmoud.elm.modules.incidents.list.domain.entity.IncidentsEntity
import com.mahmoud.elm.modules.incidents.list.domain.entity.param.IncidentsPram
import kotlinx.coroutines.flow.Flow

interface IncidentListRepository {

    fun getIncidents(param: IncidentsPram): Flow<List<IncidentsEntity>>
}