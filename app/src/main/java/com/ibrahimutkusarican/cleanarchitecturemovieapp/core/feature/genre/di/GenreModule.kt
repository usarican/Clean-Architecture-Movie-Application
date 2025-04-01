package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.database.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.data.local.GenreDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.data.remote.GenreService
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.data.repository.GenreRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.data.repository.GenreRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.domain.usecase.GetMovieGenresUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

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