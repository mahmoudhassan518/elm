package com.mahmoud.elm.modules.incidents.core.data.source

import com.mahmoud.elm.modules.core.data.source.AuthorizedApiService
import com.mahmoud.elm.modules.core.di.AuthorizedApiInterface
import com.mahmoud.elm.modules.incidents.list.domain.entity.param.IncidentsPram
import com.mahmoud.elm.modules.incidents.submit.domain.domain.SubmitIncidentPram
import javax.inject.Inject

class IncidentsRemoteDataSource @Inject constructor(@AuthorizedApiInterface private val apiService: AuthorizedApiService) {

    suspend fun getIncidents(param: IncidentsPram) = with(param) {
        apiService.getIncidents(startDate = data, status = status)
    }

    suspend fun submitIncident(param: SubmitIncidentPram) =
        apiService.submitIncident(param)

}