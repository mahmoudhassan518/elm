package com.mahmoud.elm.modules.core.di

import com.mahmoud.elm.modules.core.data.model.ElmKeys
import com.mahmoud.elm.modules.core.data.source.AuthorizedApiService
import com.mahmoud.elm.modules.core.data.source.UserManagementApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiInterFaceModule {

    @UserManagementApiInterface
    @Provides
    @Singleton
    fun provideUserManagementApiInterface(@UserManagementRetrofitClient retrofit: Retrofit): UserManagementApiService =
        retrofit.create(UserManagementApiService::class.java)

    @AuthorizedApiInterface
    @Provides
    @Singleton
    fun provideAuthorizedApiInterface(@AuthorizedRetrofitClient retrofit: Retrofit): AuthorizedApiService =
        retrofit.create(AuthorizedApiService::class.java)


    @UserManagementRetrofitClient
    @Provides
    @Singleton
    fun provideUserManagementRetrofitClient(
        @UserManagementOkhttpClient okHttpClient: OkHttpClient?,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ElmKeys.geApiUrl())
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient!!)
        .build()

    @AuthorizedRetrofitClient
    @Provides
    @Singleton
    fun provideAuthorizedRetrofitClient(
        @AuthorizedOkhttpClient okHttpClient: OkHttpClient?,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ElmKeys.geApiUrl())
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient!!)
        .build()


    @Provides
    @Singleton
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
}
