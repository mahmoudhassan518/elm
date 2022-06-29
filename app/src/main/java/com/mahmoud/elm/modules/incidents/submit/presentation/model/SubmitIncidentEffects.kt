package com.mahmoud.elm.modules.incidents.submit.presentation.model

import com.mahmoud.elm.modules.core.presentation.model.UIError

sealed class SubmitIncidentEffects {
    data class ShowResourceError(val error: UIError) : SubmitIncidentEffects()
    data class OnSubmitIncidentSuccess(val message: Int) : SubmitIncidentEffects()
}
