package com.mahmoud.elm.modules.core.di

import com.mahmoud.elm.BuildConfig.DEBUG
import com.mahmoud.elm.modules.user_management.user.data.source.UserLocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorsModule {

    @UserManagementOkhttpInterceptor
    @Provides
    @Singleton
    fun providesUserManagementOkhttpInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .build()
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @AuthorizedOkhttpInterceptor
    @Provides
    @Singleton
    fun providesAuthorizedOkhttpInterceptor(userLocalSource: UserLocalSource): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("token", userLocalSource.getUserAuthorization())
                .build()
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        if (DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
}
