package com.mahmoud.elm.core.exception

data class UnexpectedResponseException(val msg: String?) : Throwable("UnexpectedResponseException ${msg?:""}")
