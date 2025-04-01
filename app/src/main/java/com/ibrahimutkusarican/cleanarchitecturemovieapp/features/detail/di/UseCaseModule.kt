package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.usecase.GetMovieDetailUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.usecase.GetMovieDetailUseCaseImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.usecase.GetMovieShareModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.usecase.GetMovieShareModelUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindMovieDetailUseCase(getMovieDetailUseCaseImpl: GetMovieDetailUseCaseImpl): GetMovieDetailUseCase

    @Binds
    abstract fun bindGetMovieUriUseCase(getMovieUriUseCaseImpl: GetMovieShareModelUseCaseImpl): GetMovieShareModelUseCase

}