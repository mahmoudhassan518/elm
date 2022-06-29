package com.mahmoud.elm.modules.incidents.list.data.repository


import com.mahmoud.elm.base.BaseRepository
import com.mahmoud.elm.core.di.IODispatcher
import com.mahmoud.elm.core.exception.EmptyResultException
import com.mahmoud.elm.modules.incidents.core.data.source.IncidentsRemoteDataSource
import com.mahmoud.elm.modules.incidents.list.domain.repository.IncidentListRepository
import com.mahmoud.elm.modules.incidents.list.data.model.mapper.toEntity
import com.mahmoud.elm.modules.incidents.list.domain.entity.IncidentsEntity
import com.mahmoud.elm.modules.incidents.list.domain.entity.param.IncidentsPram
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IncidentListRepositoryImpl @Inject constructor(
    @IODispatcher private val dispatcher: CoroutineDispatcher,
    private val source: IncidentsRemoteDataSource
) : IncidentListRepository, BaseRepository(dispatcher) {

    override fun getIncidents(param: IncidentsPram): Flow<List<IncidentsEntity>> =
        requestHandler {
            source.getIncidents(param).incidents.map { it.toEntity() }
        }.map {
            if (it.isNullOrEmpty())
                throw EmptyResultException
            else
                it
        }
}