package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.database.VisitedMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideLastVisitedMovieDao(
        movieDatabase: MovieDatabase
    ): VisitedMovieDao = movieDatabase.lastVisitedMovieDao()

}