package com.mahmoud.elm.modules.user_management.core.di

import com.mahmoud.elm.modules.user_management.core.data.repository.UserManagementRepositoryImpl
import com.mahmoud.elm.modules.user_management.core.domain.UserManagementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserManagementDataModule {

    @Binds
    abstract fun bindUserManagementRepository(impl: UserManagementRepositoryImpl): UserManagementRepository
}
