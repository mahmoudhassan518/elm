package com.mahmoud.elm.modules.incidents.submit.presentation.viewModel

import com.mahmoud.elm.R
import com.mahmoud.elm.base.BaseViewModel
import com.mahmoud.elm.core.di.MainDispatcher
import com.mahmoud.elm.core.exception.EmptyFieldsException
import com.mahmoud.elm.modules.core.presentation.model.UIError
import com.mahmoud.elm.modules.incidents.submit.domain.domain.SubmitIncidentPram
import com.mahmoud.elm.modules.incidents.submit.domain.interactor.SubmitIncidentUseCase
import com.mahmoud.elm.modules.incidents.submit.presentation.model.SubmitIncidentEffects
import com.mahmoud.elm.modules.incidents.submit.presentation.model.SubmitIncidentEvents
import com.mahmoud.elm.modules.incidents.submit.presentation.model.SubmitIncidentUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class SubmitIncidentViewModel @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val submitIncidentUseCase: SubmitIncidentUseCase
) : BaseViewModel<SubmitIncidentUIState, SubmitIncidentEffects, SubmitIncidentEvents>(
    mainDispatcher,
    SubmitIncidentUIState()
) {

    override fun transform(event: SubmitIncidentEvents) {
        when (event) {
            is SubmitIncidentEvents.SubmitIncident -> submitIncident(event.param)
        }
    }


    private fun submitIncident(param: SubmitIncidentPram) =
        launchBlock(onStart = { onSubmitIncidentStart() }, onError = {
            it.handleErrorEffect()
        }) {
            submitIncidentUseCase(param)
            doOnSubmitIncidentSuccess()

        }

    private fun onSubmitIncidentStart() =
        setState { copy(showLoading = true) }

    private fun doOnSubmitIncidentSuccess() {
        setState { copy(showLoading = false) }
        setEffect { SubmitIncidentEffects.OnSubmitIncidentSuccess(R.string.msg_incident_submitted_successfully) }
    }

    private fun Throwable.handleErrorEffect() {
        setState { copy(showLoading = false) }
        setEffect { SubmitIncidentEffects.ShowResourceError(handleError()) }
    }


    private fun Throwable.handleError(): UIError =
        when (this) {
            is EmptyFieldsException -> UIError.hasBlankFieldsError()
            else -> UIError.getUnexpectedError()
        }

}

