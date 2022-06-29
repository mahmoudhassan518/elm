package com.mahmoud.elm.modules.incidents.submit.di

import com.mahmoud.elm.modules.incidents.submit.data.repository.SubmitIncidentRepositoryImpl
import com.mahmoud.elm.modules.incidents.submit.domain.repository.SubmitIncidentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SubmitIncidentDataModule {

    @Binds
    abstract fun bindSubmitIncidentRepository(impl: SubmitIncidentRepositoryImpl): SubmitIncidentRepository
}
