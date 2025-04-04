package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.data.UserSettingsRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.data.UserSettingsRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase.ChangeUserSettingsUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase.ChangeUserSettingsUseCaseImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase.GetSettingsModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase.GetSettingsModelUseCaseImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase.RestartAppUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase.RestartAppUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SettingsModule {
    @Binds
    abstract fun bindSettingsRepository(
        userSettingsRepositoryImpl: UserSettingsRepositoryImpl
    ): UserSettingsRepository

    @Binds
    abstract fun bindGetSettingsModelUseCase(
        getUserSettingsModelUseCaseImpl: GetSettingsModelUseCaseImpl
    ): GetSettingsModelUseCase

    @Binds
    abstract fun bindChangeUserSettingsUseCase(
        changeUserSettingsUseCaseImpl: ChangeUserSettingsUseCaseImpl
    ): ChangeUserSettingsUseCase

    @Binds
    abstract fun bindRestartAppUseCase(restartAppUseCaseImpl: RestartAppUseCaseImpl): RestartAppUseCase

}