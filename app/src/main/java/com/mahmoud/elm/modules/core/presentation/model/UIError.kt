package com.mahmoud.elm.modules.core.presentation.model

import androidx.annotation.StringRes
import com.mahmoud.elm.R

data class UIError(
    @StringRes val title: Int ,
    @StringRes val msg: Int

) {
    companion object {

        fun getUnexpectedError(): UIError {
            return UIError(
                title = R.string.error,
                msg = R.string.msgSomethingWentWrong
            )
        }

//        fun failedToGetUserDataError(): UIError {
//            return UIError(
//                title = R.string.error,
//                msg = R.string.failedGetProfileError
//            )
//        }

        fun hasBlankFieldsError(): UIError {
            return UIError(
                title = R.string.error,
                msg = R.string.emptyFields // R.string.msgWrong
            )
        }

        fun getInvalidEmailAddressError(): UIError {
            return UIError(
                title = R.string.error,
                msg = R.string.invalidEmailAddress
            )
        }
        fun getInvalidOTPError(): UIError {
            return UIError(
                title = R.string.error,
                msg = R.string.invalidOTP
            )
        }

    }
}
