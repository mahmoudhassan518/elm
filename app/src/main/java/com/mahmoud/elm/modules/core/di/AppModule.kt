package com.mahmoud.elm.modules.core.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mahmoud.elm.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.app_name).trim(),
            Context.MODE_PRIVATE
        )

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}
