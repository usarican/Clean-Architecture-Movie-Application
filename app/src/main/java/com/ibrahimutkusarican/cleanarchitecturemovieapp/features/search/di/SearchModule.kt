package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.GenreDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.LastVisitedMovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote.SearchService
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.GetSearchScreenModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.GetSearchScreenModelUseCaseImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.SearchMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.SearchMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun bindSearchSeeAllMoviesUseCase(searchSeeAllMoviesUseCaseImpl: SearchMoviesUseCaseImpl): SearchMoviesUseCase

    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun bindGetSearchScreenModelUseCase(getSearchScreenModelUseCaseImpl: GetSearchScreenModelUseCaseImpl): GetSearchScreenModelUseCase

    companion object {
        @Provides
        fun provideSearchService(retrofit: Retrofit): SearchService =
            retrofit.create(SearchService::class.java)

        @Provides
        fun provideLastVisitedMovieDao(movieDatabase: MovieDatabase) : LastVisitedMovieDao =
            movieDatabase.lastVisitedMovieDao()
    }
}