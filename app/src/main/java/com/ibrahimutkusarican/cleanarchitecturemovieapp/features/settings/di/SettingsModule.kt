package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data.UserSettingsRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data.UserSettingsRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase.ChangeUserSettingsUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase.ChangeUserSettingsUseCaseImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase.GetSettingsModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase.GetSettingsModelUseCaseImpl
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
    ) : ChangeUserSettingsUseCase

}