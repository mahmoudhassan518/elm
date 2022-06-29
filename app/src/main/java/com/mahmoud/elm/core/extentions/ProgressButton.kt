package com.mahmoud.elm.core.extentions

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.mahmoud.elm.R
import com.mahmoud.elm.databinding.ProgressButtonLayoutBinding

class ProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: ProgressButtonLayoutBinding =
        ProgressButtonLayoutBinding.inflate(LayoutInflater.from(context), this)
    var isProgress: Boolean = false
        private set
    var isBtnEnabled: Boolean = true
        private set

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton)
        val title: String = typedArray.getText(R.styleable.ProgressButton_text).toString()
        typedArray.recycle()
        binding.tvProgress.text = title
    }

    fun enableBtn(isBtnEnabled: Boolean) {
        this.isBtnEnabled = isBtnEnabled
        isClickable = isBtnEnabled
        isEnabled = isBtnEnabled
        if (isBtnEnabled)
            binding.tvProgress.setTextColor(
                AppCompatResources.getColorStateList(
                    context,
                    R.color.colorPrimary
                )
            )
        else binding.tvProgress.setTextColor(
            AppCompatResources.getColorStateList(
                context,
                R.color.colorPrimaryWithAlpha
            )
        )
    }

    fun startProgress(isProgress: Boolean) {
        binding.tvProgress.isVisible = (!isProgress)
        this.isProgress = isProgress
        binding.progress.isVisible = isProgress
        enableBtn(!isProgress)
    }

    fun setBtnText(text: String) {
        binding.tvProgress.text = text
    }
}
