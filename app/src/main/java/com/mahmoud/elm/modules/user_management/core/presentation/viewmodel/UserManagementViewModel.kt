package com.mahmoud.elm.modules.user_management.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mahmoud.elm.modules.user_management.core.presentation.model.UserManagementAppBarSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class UserManagementViewModel @Inject constructor(
) : ViewModel() {


    private val _appBarSettingsEvent: MutableStateFlow<UserManagementAppBarSettings> =
        MutableStateFlow(UserManagementAppBarSettings())
    val appBarSettingsEvent = _appBarSettingsEvent.asSharedFlow()

    fun updateAppBarSettings(settings: UserManagementAppBarSettings) {
        _appBarSettingsEvent.value = settings
    }


}
