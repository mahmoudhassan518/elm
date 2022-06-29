package com.mahmoud.elm.modules.core.presentation.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.mahmoud.elm.R
import com.mahmoud.elm.databinding.LayoutIncidentStatusViewBinding

class IncidentStatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var binding: LayoutIncidentStatusViewBinding =
        LayoutIncidentStatusViewBinding.inflate(LayoutInflater.from(context), null, false)

    init {
        addView(binding.root)
        init(attrs)
    }

    private fun init(set: AttributeSet?) {
        val ta = context.obtainStyledAttributes(set, R.styleable.view_incident_status)
        binding.tvKey.text = ta.getString(R.styleable.view_incident_status_item_label)
        ta.recycle()
    }

    fun setKey(key: String) {
        binding.tvKey.text = key
    }

    fun setValue(value: String?) {
        binding.tvValue.text = value
    }
}
