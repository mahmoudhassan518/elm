package com.mahmoud.elm.modules.incidents.list.presentation.viewmodel

import com.mahmoud.elm.R
import com.mahmoud.elm.base.BaseViewModel
import com.mahmoud.elm.core.di.MainDispatcher
import com.mahmoud.elm.core.exception.EmptyResultException
import com.mahmoud.elm.modules.incidents.list.domain.entity.IncidentsEntity
import com.mahmoud.elm.modules.incidents.list.domain.entity.param.IncidentsPram
import com.mahmoud.elm.modules.incidents.list.domain.interactor.GetIncidentsListUseCase
import com.mahmoud.elm.modules.incidents.list.presentation.model.IncidentListEffects
import com.mahmoud.elm.modules.incidents.list.presentation.model.IncidentListEvents
import com.mahmoud.elm.modules.incidents.list.presentation.model.IncidentListUIState
import com.mahmoud.elm.modules.incidents.list.presentation.model.StatusTypeEnum
import com.mahmoud.elm.modules.incidents.list.presentation.model.mapper.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class IncidentListViewModel @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val getIncidentsListUseCase: GetIncidentsListUseCase
) : BaseViewModel<IncidentListUIState, IncidentListEffects, IncidentListEvents>(
    mainDispatcher,
    IncidentListUIState()
) {

    override fun transform(event: IncidentListEvents) {
        when (event) {
            is IncidentListEvents.ShowCalenderDialog -> showCalenderDialog()
            is IncidentListEvents.GetIncidentList -> getIncidentList()
            is IncidentListEvents.GetIncidentListByDate -> getIncidentListByDate(event.date)
            is IncidentListEvents.GetIncidentListByStatue -> getIncidentListByStatue(event.status)
        }
    }


    private fun getIncidentList() =
        launchBlock(onStart = { onGetIncidentListStart() }, onError = {
            it.handleErrorState()
        }) {
            getIncidentsListUseCase(
                IncidentsPram(
                    data = currentState.startDateFilter,
                    status = currentState.currentStatusFilter?.value.toString()
                )
            ).collectLatest {
                it.doOnGetIncidentListSuccess()
            }

        }

    private fun getIncidentListByDate(cal: Calendar) {
        setState {
            copy(startDateFilter = cal.getDateStringFromCalender(), cal = cal)
        }
        getIncidentList()
    }


    private fun getIncidentListByStatue(status: StatusTypeEnum) {
        setState {
            copy(currentStatusFilter = status)
        }
        getIncidentList()
    }


    private fun showCalenderDialog() =
        setEffect { IncidentListEffects.ShowCalender(currentState.cal) }

    private fun onGetIncidentListStart() =
        setState { copy(showLoading = true, errorMessage = null, emptyMessage = null) }

    private fun List<IncidentsEntity>.doOnGetIncidentListSuccess() {
        setState { copy(showLoading = false, incidents = map { it.toUIState() }.toMutableList()) }
    }

    private fun Throwable.handleErrorState() {
        setState {
            copy(
                showLoading = false,
                errorMessage = handleError(),
                emptyMessage = handleEmptyResult()
            )
        }
    }

    private fun Throwable.handleError(): Int? =
        when (this) {
            is EmptyResultException -> null
            else -> R.string.msgSomethingWentWrong
        }

    private fun Throwable.handleEmptyResult(): Int? =
        when (this) {
            is EmptyResultException -> R.string.msg_empty_incident_list
            else -> null
        }

    private fun Calendar.getDateStringFromCalender(): String =
        SimpleDateFormat("MM-dd-yyyy", Locale.US).format(time)

}