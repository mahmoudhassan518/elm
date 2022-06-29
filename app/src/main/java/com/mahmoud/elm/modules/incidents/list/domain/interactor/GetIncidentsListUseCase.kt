package com.mahmoud.elm.modules.incidents.list.domain.interactor

import com.mahmoud.elm.base.FlowUseCase
import com.mahmoud.elm.modules.incidents.list.domain.repository.IncidentListRepository
import com.mahmoud.elm.modules.incidents.list.domain.entity.IncidentsEntity
import com.mahmoud.elm.modules.incidents.list.domain.entity.param.IncidentsPram
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIncidentsListUseCase @Inject constructor(
    private val repository: IncidentListRepository,
) : FlowUseCase<IncidentsPram, List<IncidentsEntity>> {

    override fun invoke(param: IncidentsPram): Flow<List<IncidentsEntity>> =
        repository.getIncidents(param)

}