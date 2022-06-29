package com.mahmoud.elm.modules.core.presentation.navigation

interface NavigationCoordinator<T> {

    fun onStart()
    fun init(param: Any?)
    fun onEvent(event: T)
}
