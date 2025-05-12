package com.iusarican.data.di

import com.iusarican.data.repository.GenreRepositoryImpl
import com.iusarican.domain.repository.GenreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGenreRepository(genreRepositoryImpl: GenreRepositoryImpl): GenreRepository
}