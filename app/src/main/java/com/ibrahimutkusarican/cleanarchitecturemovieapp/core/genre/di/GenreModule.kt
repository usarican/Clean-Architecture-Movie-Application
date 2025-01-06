package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.GenreDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.remote.GenreService
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository.GenreRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository.GenreRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GenreModule {

    @Binds
    abstract fun bindGenreRepository(genreRepositoryImpl: GenreRepositoryImpl): GenreRepository

    @Binds
    abstract fun bindGetMovieGenresUseCase(getMovieGenresUseCaseImpl: GetMovieGenresUseCaseImpl): GetMovieGenresUseCase

    companion object {

        @Provides
        fun provideGenreService(retrofit: Retrofit): GenreService =
            retrofit.create(GenreService::class.java)

        @Provides
        fun provideGenreDao(movieDatabase: MovieDatabase) : GenreDao =
            movieDatabase.genreDao()
    }
}