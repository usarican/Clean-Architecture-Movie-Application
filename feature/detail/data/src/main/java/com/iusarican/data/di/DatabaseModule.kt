package com.iusarican.data.di


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