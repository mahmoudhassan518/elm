package com.mahmoud.elm.modules.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @UserManagementOkhttpClient
    @Provides
    @Singleton
    fun provideUserManagementOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @UserManagementOkhttpInterceptor interceptor: Interceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient.Builder()
            .followRedirects(false)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()

    @AuthorizedOkhttpClient
    @Provides
    @Singleton
    fun provideAuthorizedOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @AuthorizedOkhttpInterceptor interceptor: Interceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient.Builder()
            .followRedirects(false)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()


    @Provides
    @Singleton
    fun cacheFile(@ApplicationContext context: Context): File =
        File(context.cacheDir, "okHttp_cache")

    @Provides
    @Singleton
    fun cache(file: File?): Cache =
        Cache(file!!, (10 * 1000 * 1000)) // 10MB cache
}
