package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.MovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {

    companion object {
        @Provides
        @Singleton
        fun provideMovieService(retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)

        @Provides
        @Singleton
        fun provideMovieDao(movieDatabase: MovieDatabase) : MovieDao = movieDatabase.movieDao()

    }
}