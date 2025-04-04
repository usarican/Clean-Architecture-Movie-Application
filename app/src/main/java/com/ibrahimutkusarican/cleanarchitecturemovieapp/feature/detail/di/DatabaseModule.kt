package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.database.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.database.VisitedMovieDao
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