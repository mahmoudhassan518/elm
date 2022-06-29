package com.mahmoud.elm.modules.user_management.sign_in.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mahmoud.elm.modules.core.presentation.navigation.NavigationCoordinator
import com.mahmoud.elm.R
import com.mahmoud.elm.base.BaseFragment
import com.mahmoud.elm.core.extentions.showAlerterError
import com.mahmoud.elm.databinding.FragmentSignInBinding
import com.mahmoud.elm.modules.user_management.core.presentation.model.UserManagementAppBarSettings
import com.mahmoud.elm.modules.incidents.core.presentation.navigation.IncidentsNavigatorEvents
import com.mahmoud.elm.modules.user_management.core.presentation.navigation.UserManagementNavigatorEvents
import com.mahmoud.elm.modules.user_management.core.presentation.viewmodel.UserManagementViewModel
import com.mahmoud.elm.modules.user_management.sign_in.presentation.model.SignInEffects
import com.mahmoud.elm.modules.user_management.sign_in.presentation.model.SignInEvents
import com.mahmoud.elm.modules.user_management.sign_in.presentation.model.SignInUIState
import com.mahmoud.elm.modules.user_management.sign_in.presentation.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment :
    BaseFragment<FragmentSignInBinding, SignInUIState, SignInEffects, SignInEvents, SignInViewModel>() {
    override val viewModel: SignInViewModel by viewModels()
    private val userManagementViewModel: UserManagementViewModel by activityViewModels()

    @Inject
    lateinit var navigator: NavigationCoordinator<UserManagementNavigatorEvents>

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): FragmentSignInBinding =
        FragmentSignInBinding.inflate(layoutInflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        userManagementViewModel.updateAppBarSettings(
            UserManagementAppBarSettings(
                isBackIconVisible = false,
                R.string.title_sign_in
            )
        )
        initActions()
        initObservations()
    }

    private fun initActions() {
        binding.btnProgress.setOnClickListener {
            viewModel.setEvent(SignInEvents.SignIn(binding.edtEmail.text.toString()))
        }
    }


    private fun initObservations() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collectLatest {
                renderEffects(it)
            }
        }

    }

    private fun renderEffects(it: SignInEffects) =
        when (it) {
            is SignInEffects.ShowResourceError -> requireActivity().showAlerterError(it.error)
            is SignInEffects.NavigateToValidateOTPScreen -> navigateToValidateScreen(it.email)
    }

    override fun render(ui: SignInUIState) {
        binding.btnProgress.startProgress(ui.showLoading)
    }


    private fun navigateToValidateScreen(email: String) =
        navigator.onEvent(UserManagementNavigatorEvents.OpenValidateOTPScreen(email))


}
