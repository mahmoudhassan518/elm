package com.mahmoud.elm.core.extentions

import android.app.Activity
import android.content.res.Configuration
import androidx.core.content.ContextCompat
import com.mahmoud.elm.R
import com.mahmoud.elm.modules.core.presentation.model.UIError
import com.tapadoo.alerter.Alerter


fun Activity.showAlerterError(
    error: UIError,
) {
    Alerter.create(this)
        .setIcon(R.drawable.ic_failed)
        .setTitle(error.title)
        .setTitleAppearance(R.style.AlertTextAppearance_Title)
        .setText(error.msg)
        .setTextAppearance(R.style.AlertTextAppearance_Text)
        .setBackgroundColorInt(ContextCompat.getColor(applicationContext, R.color.colorError))
        .enableSwipeToDismiss()
        .show()
}

fun Activity.showAlerterSuccess(
    errorMessage: String
) {
    Alerter.create(this)
        .setIcon(R.drawable.ic_check)
        .setTitle(getString(R.string.label_done))
        .setTitleAppearance(R.style.AlertTextAppearance_Title)
        .setText(errorMessage)
        .setTextAppearance(R.style.AlertTextAppearance_Text)
        .setBackgroundColorInt(ContextCompat.getColor(applicationContext, R.color.colorSuccess))
        .enableSwipeToDismiss()
        .show()
}

fun Activity.isTablet(): Boolean {
    return ((resources.configuration.screenLayout
            and Configuration.SCREENLAYOUT_SIZE_MASK)
            >= Configuration.SCREENLAYOUT_SIZE_LARGE)
}