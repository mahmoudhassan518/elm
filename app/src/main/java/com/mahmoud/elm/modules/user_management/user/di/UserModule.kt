package com.mahmoud.elm.modules.user_management.user.di

import com.mahmoud.elm.modules.user_management.user.data.repository.UserRepositoryImpl
import com.mahmoud.elm.modules.user_management.user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}
