package com.mahmoud.elm.base

import com.mahmoud.elm.core.exception.UnexpectedResponseException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class BaseRepository(
    private val ioDispatcher: CoroutineDispatcher
) {

    fun <T> requestHandler(fetch: suspend () -> T) = flow {
        try {
            emit(fetch.invoke())
        } catch (throwable: Throwable) {
            Timber.e("error happen in requestHandler $throwable")
            throw UnexpectedResponseException(throwable.message)
        }
    }.flowOn(ioDispatcher)


    suspend fun <T> runOnIO(fetch: suspend () -> T): T {
        return withContext(ioDispatcher) {
            try {
                fetch.invoke()
            } catch (throwable: Throwable) {
                Timber.e("error happen in runOnIO $throwable")
                throw UnexpectedResponseException(throwable.message)
            }
        }
    }


}
