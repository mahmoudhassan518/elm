package com.mahmoud.elm.modules.incidents.submit.domain.interactor

import com.mahmoud.elm.base.SuspendUseCase
import com.mahmoud.elm.core.exception.EmptyFieldsException
import com.mahmoud.elm.modules.incidents.submit.domain.domain.SubmitIncidentPram
import com.mahmoud.elm.modules.incidents.submit.domain.repository.SubmitIncidentRepository
import javax.inject.Inject

class SubmitIncidentUseCase @Inject constructor(
    private val repository: SubmitIncidentRepository,
) : SuspendUseCase<SubmitIncidentPram, Unit> {

    override suspend fun invoke(param: SubmitIncidentPram): Unit =
        with(param) {
            if (hasEmptyFields())
                throw EmptyFieldsException
            else
                repository.submitIncident(param)
        }

    private fun SubmitIncidentPram.hasEmptyFields() =
        this.description.trim().isEmpty() || this.lat.trim().isEmpty() || this.lon.trim().isEmpty()

}