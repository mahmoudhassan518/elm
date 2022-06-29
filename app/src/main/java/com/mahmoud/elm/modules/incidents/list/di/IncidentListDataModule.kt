package com.mahmoud.elm.modules.incidents.list.di

import com.mahmoud.elm.modules.incidents.list.data.repository.IncidentListRepositoryImpl
import com.mahmoud.elm.modules.incidents.list.domain.repository.IncidentListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class IncidentListDataModule {

    @Binds
    abstract fun bindIncidentListRepository(impl: IncidentListRepositoryImpl): IncidentListRepository
}
