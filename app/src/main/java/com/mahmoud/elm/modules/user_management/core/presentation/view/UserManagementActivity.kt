package com.mahmoud.elm.modules.user_management.core.presentation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mahmoud.elm.modules.core.presentation.navigation.NavigationCoordinator
import com.mahmoud.elm.R
import com.mahmoud.elm.databinding.ActivityUserManagementBinding
import com.mahmoud.elm.modules.user_management.core.presentation.model.UserManagementAppBarSettings
import com.mahmoud.elm.modules.incidents.core.presentation.navigation.IncidentsNavigatorEvents
import com.mahmoud.elm.modules.user_management.core.presentation.navigation.UserManagementNavigatorEvents
import com.mahmoud.elm.modules.user_management.core.presentation.viewmodel.UserManagementViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class UserManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserManagementBinding

    private val viewModel: UserManagementViewModel by viewModels()

    @Inject
    lateinit var navigator: NavigationCoordinator<UserManagementNavigatorEvents>

    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_user_management_fragment) as NavHostFragment
        navHostFragment.navController
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigator.init(navController)

        initActions()
        initObservations()
    }

    private fun initActions() {
        binding.layoutBackBar.ibBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initObservations() {
        lifecycleScope.launchWhenStarted {
            viewModel.appBarSettingsEvent.collectLatest {
                updateAppBarStatus(it)
            }
        }
    }

    private fun updateAppBarStatus(it: UserManagementAppBarSettings) {
        binding.layoutBackBar.ibBack.isVisible = it.isBackIconVisible
        binding.layoutBackBar.tvTitle.text = getString(it.title)
    }

    companion object {

        fun startActivity(activity: Activity) {
            val mainIntent = Intent(activity, UserManagementActivity::class.java)
            activity.startActivity(mainIntent)
        }
    }
}
