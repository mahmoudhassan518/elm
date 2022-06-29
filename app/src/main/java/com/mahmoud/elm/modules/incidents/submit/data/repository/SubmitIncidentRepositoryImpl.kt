package com.mahmoud.elm.modules.incidents.submit.data.repository


import com.mahmoud.elm.base.BaseRepository
import com.mahmoud.elm.core.di.IODispatcher
import com.mahmoud.elm.modules.incidents.core.data.source.IncidentsRemoteDataSource
import com.mahmoud.elm.modules.incidents.submit.domain.domain.SubmitIncidentPram
import com.mahmoud.elm.modules.incidents.submit.domain.repository.SubmitIncidentRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SubmitIncidentRepositoryImpl @Inject constructor(
    @IODispatcher private val dispatcher: CoroutineDispatcher,
    private val source: IncidentsRemoteDataSource
) : SubmitIncidentRepository, BaseRepository(dispatcher) {

    override suspend fun submitIncident(param: SubmitIncidentPram) = runOnIO {
        source.submitIncident(param)
    }
}