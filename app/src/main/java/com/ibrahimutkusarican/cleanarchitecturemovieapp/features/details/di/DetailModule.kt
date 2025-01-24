package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.MovieDetailRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.MovieDetailRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local.VisitedMovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieDetailService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class DetailModule {
    @Binds
    abstract fun bindMovieDetailRepository(movieDetailRepositoryImpl: MovieDetailRepositoryImpl): MovieDetailRepository

    companion object {
        @Provides
        fun provideMovieDetailService(retrofit: Retrofit): MovieDetailService {
            return retrofit.create(MovieDetailService::class.java)
        }

        @Provides
        fun provideLastVisitedMovieDao(movieDatabase: MovieDatabase): VisitedMovieDao =
            movieDatabase.lastVisitedMovieDao()
    }
}