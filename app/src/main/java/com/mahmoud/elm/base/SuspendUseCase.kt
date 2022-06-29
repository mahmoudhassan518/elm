package com.mahmoud.elm.base

interface SuspendUseCase<PARAM, TYPE> {
    suspend operator fun invoke(param: PARAM): TYPE
}
