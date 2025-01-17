package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.usecase.GetExploreInitialDataUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.usecase.GetExploreInitialDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ExploreModule {

    @Binds
    abstract fun bindGetExploreInitialDataUseCase(getExploreInitialDataUseCaseImpl: GetExploreInitialDataUseCaseImpl) : GetExploreInitialDataUseCase
}