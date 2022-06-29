package com.mahmoud.elm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collectLatest


abstract class BaseFragment<binding : ViewBinding, STATE, EFFECT, EVENT, VM : BaseViewModel<STATE, EFFECT, EVENT>> :
    Fragment() {

    protected abstract val viewModel: VM

    private var _binding: binding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindView(inflater = inflater, container = container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(savedInstanceState)
        observeOnUIUpdates()
        viewModel.subscribeEvents()
    }

    private fun observeOnUIUpdates() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { uiState ->
                render(uiState)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun bindView(inflater: LayoutInflater, container: ViewGroup?): binding

    abstract fun render(ui: STATE)
    abstract fun setup(savedInstanceState: Bundle?)

}