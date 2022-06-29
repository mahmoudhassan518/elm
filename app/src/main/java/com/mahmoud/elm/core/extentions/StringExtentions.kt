package com.mahmoud.elm.core.extentions

import com.mahmoud.elm.core.utils.Action
import java.util.regex.Pattern

fun Any?.isNull(function: Action) {
    if (this == null)
        function.invoke()
}

fun String.isValidEmail(): Boolean {
    val pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    return pattern.matcher(this).matches()
}

fun String.isValidOTP(): Boolean = this.length == 4


