package com.mahmoud.elm.modules.incidents.submit.domain.repository

import com.mahmoud.elm.modules.incidents.submit.domain.domain.SubmitIncidentPram

interface SubmitIncidentRepository {

    suspend fun submitIncident(param: SubmitIncidentPram)
}