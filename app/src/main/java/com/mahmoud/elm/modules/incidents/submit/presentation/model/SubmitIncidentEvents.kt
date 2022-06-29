package com.mahmoud.elm.modules.incidents.submit.presentation.model

import com.mahmoud.elm.modules.incidents.submit.domain.domain.SubmitIncidentPram

sealed class SubmitIncidentEvents {
    data class SubmitIncident(val param : SubmitIncidentPram) :
        SubmitIncidentEvents()
}
