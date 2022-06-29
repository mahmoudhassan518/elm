package com.mahmoud.elm.modules.incidents.list.presentation.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoud.elm.R
import com.mahmoud.elm.base.BaseActivity
import com.mahmoud.elm.databinding.ActivityIncidentListBinding
import com.mahmoud.elm.modules.core.presentation.navigation.NavigationCoordinator
import com.mahmoud.elm.modules.incidents.core.presentation.navigation.IncidentsNavigatorEvents
import com.mahmoud.elm.modules.incidents.list.presentation.adapter.IncidentListAdapter
import com.mahmoud.elm.modules.incidents.list.presentation.model.IncidentListEffects
import com.mahmoud.elm.modules.incidents.list.presentation.model.IncidentListEvents
import com.mahmoud.elm.modules.incidents.list.presentation.model.IncidentListUIState
import com.mahmoud.elm.modules.incidents.list.presentation.model.StatusTypeEnum
import com.mahmoud.elm.modules.incidents.list.presentation.viewmodel.IncidentListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class IncidentsListActivity :
    BaseActivity<ActivityIncidentListBinding, IncidentListUIState, IncidentListEffects, IncidentListEvents, IncidentListViewModel>() {
    override val viewModel: IncidentListViewModel by viewModels()

    @Inject
    lateinit var navigator: NavigationCoordinator<IncidentsNavigatorEvents>

    @Inject
    lateinit var incidentListAdapter: IncidentListAdapter

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val calendar: Calendar = Calendar.getInstance()

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)


            viewModel.setEvent(IncidentListEvents.GetIncidentListByDate(calendar))

        }


    override fun setup() {
        initActions()
        initViews()
    }

    override fun onResume() {
        super.onResume()

        getIncidentList()
    }

    private fun initViews() {
        initRecyclerViewAdapter()
        initSwapRefresher()

        binding.layoutBackBar.ibBack.isVisible = false
        binding.layoutBackBar.tvTitle.text = getString(R.string.incidents)
    }

    private fun initRecyclerViewAdapter() {
        with(binding.rvIncidents) {
            layoutManager = LinearLayoutManager(context)
            adapter = incidentListAdapter
        }
    }

    private fun initSwapRefresher() {
        binding.swContainer.setOnRefreshListener {
            binding.swContainer.isRefreshing = false
            getIncidentList()
        }
    }

    private fun initActions() {
        binding.btnSubmitIncident.setOnClickListener {
            navigator.onEvent(IncidentsNavigatorEvents.OpenSubmitIncidentScreen)
        }

        binding.btnDate.setOnClickListener {
            viewModel.setEvent(IncidentListEvents.ShowCalenderDialog)
        }

        binding.btnStatus.setOnClickListener {
            viewModel.setEvent(
                IncidentListEvents.GetIncidentListByStatue(
                    StatusTypeEnum.COMPLETED
                    // todo this have to be fetched from some kind of dialog or drop down list
                )
            )
        }

    }


    override fun bindView(): ActivityIncidentListBinding =
        ActivityIncidentListBinding.inflate(layoutInflater)

    override fun renderState(state: IncidentListUIState): Unit = with(state) {
        binding.layoutStateView.showLoading(showLoading)
        errorMessage?.let {
            binding.layoutStateView.showError(
                getString(it),
                retry = { getIncidentList() })
        }
        emptyMessage?.let {
            binding.layoutStateView.showEmpty(
                getString(it)
            )
        }

        incidentListAdapter.submitData(incidents)
    }

    override fun renderEffect(effect: IncidentListEffects) = when (effect) {
        is IncidentListEffects.ShowCalender -> showCalender(effect.cal)
    }

    private fun showCalender(cal: Calendar) =
        DatePickerDialog(
            this,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()


    private fun getIncidentList() = viewModel.setEvent(IncidentListEvents.GetIncidentList)


    companion object {
        fun startActivity(activity: Activity) {
            val mainIntent = Intent(activity, IncidentsListActivity::class.java)
            activity.startActivity(mainIntent)
            activity.finish()
        }
    }


}