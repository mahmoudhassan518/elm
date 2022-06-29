package com.mahmoud.elm.core.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.mahmoud.elm.core.utils.Action
import com.mahmoud.elm.databinding.LayoutStateViewBinding

class StateViewLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var binding: LayoutStateViewBinding =
        LayoutStateViewBinding.inflate(LayoutInflater.from(context), null, false)

    init {
        addView(binding.root)
    }

    fun showLoading(loading: Boolean) {
        binding.root.isVisible = loading
        binding.cvLoading.isVisible = loading
        binding.clError.isVisible = false
        binding.clEmpty.isVisible = false
    }

    fun showError(errorMessage: String?, retry: Action? = null) {
        binding.clError.isVisible = errorMessage != null
        binding.root.isVisible = errorMessage != null
        binding.cvLoading.isVisible = false
        binding.clEmpty.isVisible = false
        binding.tvErrorMessage.text = errorMessage
        binding.tvRetry.setOnClickListener {
            retry?.invoke()
        }
    }

    fun showEmpty(emptyMessage: String?, @DrawableRes emptyIcon: Int? = null) {
        binding.clError.isVisible = false
        binding.root.isVisible = emptyMessage != null
        binding.cvLoading.isVisible = false

        binding.tvEmptyMessage.text = emptyMessage

        emptyIcon?.let {
            binding.ivEmpty.setImageResource(emptyIcon)
        }
    }


    fun setStateViewBackgroundColor(backgroundColor: Int) =
        binding.root.setBackgroundColor(
            ContextCompat.getColor(
                context,
                backgroundColor
            )
        )
}
