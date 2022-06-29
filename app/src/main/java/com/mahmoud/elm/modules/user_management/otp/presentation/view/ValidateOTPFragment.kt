package com.mahmoud.elm.modules.user_management.otp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.mahmoud.elm.R
import com.mahmoud.elm.base.BaseFragment
import com.mahmoud.elm.core.extentions.showAlerterError
import com.mahmoud.elm.databinding.FragmentValidateOtpBinding
import com.mahmoud.elm.modules.core.presentation.navigation.NavigationCoordinator
import com.mahmoud.elm.modules.user_management.core.presentation.model.UserManagementAppBarSettings
import com.mahmoud.elm.modules.user_management.core.presentation.navigation.UserManagementNavigatorEvents
import com.mahmoud.elm.modules.user_management.core.presentation.viewmodel.UserManagementViewModel
import com.mahmoud.elm.modules.user_management.otp.presentation.model.ValidateOTPEffects
import com.mahmoud.elm.modules.user_management.otp.presentation.model.ValidateOTPEvents
import com.mahmoud.elm.modules.user_management.otp.presentation.model.ValidateOTPUIState
import com.mahmoud.elm.modules.user_management.otp.presentation.viewmodel.ValidateOTPViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class ValidateOTPFragment :
    BaseFragment<FragmentValidateOtpBinding, ValidateOTPUIState, ValidateOTPEffects, ValidateOTPEvents, ValidateOTPViewModel>() {

    override val viewModel: ValidateOTPViewModel by viewModels()
    private val userManagementViewModel: UserManagementViewModel by activityViewModels()

    val args: ValidateOTPFragmentArgs by navArgs()

    @Inject
    lateinit var navigator: NavigationCoordinator<UserManagementNavigatorEvents>

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentValidateOtpBinding =
        FragmentValidateOtpBinding.inflate(layoutInflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        userManagementViewModel.updateAppBarSettings(
            UserManagementAppBarSettings(
                isBackIconVisible = true,
                R.string.title_validate_otp
            )
        )
        initActions()
        initObservations()
    }

    private fun initActions() {
        binding.btnProgress.setOnClickListener {
            viewModel.setEvent(
                ValidateOTPEvents.ValidateOTP(
                    email = args.email,
                    otp = binding.edtOtp.text.toString()
                )
            )
        }
    }


    private fun initObservations() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collectLatest {
                renderEffects(it)
            }
        }

    }

    private fun renderEffects(it: ValidateOTPEffects) {
        when (it) {
            is ValidateOTPEffects.ShowResourceError -> requireActivity().showAlerterError(it.error)
            is ValidateOTPEffects.NavigateToHomeScreen -> navigateToHomeScreen()
        }
    }

    override fun render(ui: ValidateOTPUIState) {
        binding.btnProgress.startProgress(ui.showLoading)
    }


    private fun navigateToHomeScreen() =
        navigator.onEvent(UserManagementNavigatorEvents.OpenHomeScreen)


}
