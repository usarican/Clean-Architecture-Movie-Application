package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.domain.RestartAppUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.domain.RestartAppUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainModule {

    @Binds
    abstract fun bindRestartAppUseCase(restartAppUseCaseImpl: RestartAppUseCaseImpl): RestartAppUseCase

}