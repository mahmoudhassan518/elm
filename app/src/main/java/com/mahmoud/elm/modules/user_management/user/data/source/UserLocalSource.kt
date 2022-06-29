package com.mahmoud.elm.modules.user_management.user.data.source

import android.content.SharedPreferences
import com.google.gson.Gson
import com.mahmoud.elm.modules.user_management.user.domain.entity.UserEntity
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class UserLocalSource @Inject constructor(
    private val sharedPref: SharedPreferences,
    private val gson: Gson
) {

    suspend fun getUser() = suspendCancellableCoroutine<UserEntity> {
        val json = sharedPref.getString(USER, null)
        val user: UserEntity? = gson.fromJson(json, UserEntity::class.java)
        if (user == null)
            it.resumeWithException(Throwable("user is Null Value "))
        else
            it.resume(user)
    }

    suspend fun saveUser(params: UserEntity) = suspendCancellableCoroutine<UserEntity> {
        val json = gson.toJson(params)
        with(sharedPref.edit()) {
            putString(
                USER,
                json
            )
            apply()
            it.resume(params)
        }
    }

    suspend fun deleteUser() = suspendCancellableCoroutine<Unit> {
        with(sharedPref.edit()) {
            putString(USER, null)
            commit()
            it.resume(Unit)
        }
    }

    fun getUserAuthorization(): String? {
        val json = sharedPref.getString(USER, null)
        return gson.fromJson(json, UserEntity::class.java)?.accessToken
    }

    companion object {
        private const val USER = "USER"
    }
}
