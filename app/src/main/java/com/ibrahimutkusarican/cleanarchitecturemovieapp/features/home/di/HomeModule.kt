package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.MovieRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.MovieRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.MovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.MovieService
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.GetHomeMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.GetHomeMoviesUseCaseImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.RefreshHomeMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.RefreshHomeMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindGetHomeMoviesUseCase(getHomeMoviesUseCaseImpl: GetHomeMoviesUseCaseImpl): GetHomeMoviesUseCase

    @Binds
    abstract fun bindRefreshHomeMoviesUseCase(refreshHomeMoviesUseCaseImpl: RefreshHomeMoviesUseCaseImpl): RefreshHomeMoviesUseCase


    companion object {
        @Provides
        fun provideMovieService(retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)

        @Provides
        fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()

    }
}