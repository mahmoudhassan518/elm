package com.mahmoud.elm.modules.incidents.list.presentation.model

import com.mahmoud.elm.R

enum class StatusTypeEnum(val value: Int, val text: Int) {
    SUBMITTED(value = 0, text = R.string.label_submitted),
    IN_PROGRESS(value = 1, text = R.string.label_inProgress),
    COMPLETED(value = 2, text = R.string.label_completed),
    REJECTED(value = 3, text = R.string.label_rejected)


}