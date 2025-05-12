package com.iusarican.data.di

import com.iusarican.data.local.GenreDao
import com.iusarican.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideGenreDao(movieDatabase: MovieDatabase) : GenreDao =
        movieDatabase.genreDao()
}