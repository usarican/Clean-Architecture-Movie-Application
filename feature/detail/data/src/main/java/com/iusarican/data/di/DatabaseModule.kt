package com.iusarican.data.di


import com.iusarican.data.database.VisitedMovieDao
import com.iusarican.database.MovieDatabase
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