package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase.GetCachedSeeAllMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase.GetCachedSeeAllMoviesUseCaseUseCaseImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase.GetPagingSeeAllMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase.GetPagingSeeAllMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelScoped::class)
abstract class SeeAllModule {
    @Binds
    abstract fun bindSeeAllRepository(seeAllRepositoryImpl: SeeAllRepositoryImpl): SeeAllRepository

   @Binds
   abstract fun bindGetCachedSeeAllMoviesUseCase(getCachedSeeAllMoviesUseCaseImpl: GetCachedSeeAllMoviesUseCaseUseCaseImpl): GetCachedSeeAllMoviesUseCase

   @Binds
   abstract fun bingGetPagingSeeAllMoviesUseCase(getPagingSeeAllMoviesUseCaseImpl: GetPagingSeeAllMoviesUseCaseImpl): GetPagingSeeAllMoviesUseCase

}