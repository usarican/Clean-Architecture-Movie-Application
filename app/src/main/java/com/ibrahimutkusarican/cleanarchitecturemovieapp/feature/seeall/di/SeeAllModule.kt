package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.SeeAllRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.SeeAllRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.usecase.GetSeeAllUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.usecase.GetSeeAllUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SeeAllModule {
    @Binds
    abstract fun bindSeeAllRepository(seeAllRepositoryImpl: SeeAllRepositoryImpl): SeeAllRepository

   @Binds
   abstract fun bingGetPagingSeeAllMoviesUseCase(getPagingSeeAllMoviesUseCaseImpl: GetSeeAllUseCaseImpl): GetSeeAllUseCase

}