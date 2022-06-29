package com.mahmoud.elm.modules.incidents.submit.presentation.view

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.mahmoud.elm.R
import com.mahmoud.elm.base.BaseActivity
import com.mahmoud.elm.core.extentions.showAlerterError
import com.mahmoud.elm.core.extentions.showAlerterSuccess
import com.mahmoud.elm.databinding.ActivitySubmitIncidentBinding
import com.mahmoud.elm.modules.core.presentation.navigation.NavigationCoordinator
import com.mahmoud.elm.modules.incidents.core.presentation.navigation.IncidentsNavigatorEvents
import com.mahmoud.elm.modules.incidents.submit.domain.domain.SubmitIncidentPram
import com.mahmoud.elm.modules.incidents.submit.presentation.model.SubmitIncidentEffects
import com.mahmoud.elm.modules.incidents.submit.presentation.model.SubmitIncidentEvents
import com.mahmoud.elm.modules.incidents.submit.presentation.model.SubmitIncidentUIState
import com.mahmoud.elm.modules.incidents.submit.presentation.viewModel.SubmitIncidentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SubmitIncidentActivity :
    BaseActivity<ActivitySubmitIncidentBinding, SubmitIncidentUIState, SubmitIncidentEffects, SubmitIncidentEvents, SubmitIncidentViewModel>() {
    override val viewModel: SubmitIncidentViewModel by viewModels()

    @Inject
    lateinit var navigator: NavigationCoordinator<IncidentsNavigatorEvents>

    override fun setup() {
        initActions()
        initViews()
    }

    private fun initViews() {
        binding.layoutBackBar.tvTitle.text = getString(R.string.title_submit_incident)
    }


    private fun initActions() {
        binding.layoutBackBar.ibBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnSubmit.setOnClickListener {
            submitNewIncident(
                SubmitIncidentPram(
                    lat = binding.edtLatitude.text.toString(),
                    lon = binding.edtLatitude.text.toString(),
                    description = binding.edtDescription.text.toString()
                )
            )
        }
    }


    override fun bindView(): ActivitySubmitIncidentBinding =
        ActivitySubmitIncidentBinding.inflate(layoutInflater)

    override fun renderState(state: SubmitIncidentUIState) {
        binding.btnSubmit.startProgress(state.showLoading)
    }

    override fun renderEffect(effect: SubmitIncidentEffects) =
        when (effect) {
            is SubmitIncidentEffects.ShowResourceError -> showAlerterError(effect.error)
            is SubmitIncidentEffects.OnSubmitIncidentSuccess -> showAlerterSuccess(getString(effect.message)).also { finish() }
        }

    private fun submitNewIncident(param: SubmitIncidentPram) =
        viewModel.setEvent(SubmitIncidentEvents.SubmitIncident(param))

    companion object {
        fun startActivity(activity: Activity) {
            val mainIntent = Intent(activity, SubmitIncidentActivity::class.java)
            activity.startActivity(mainIntent)
        }
    }


}