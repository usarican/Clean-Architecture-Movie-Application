package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchService
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.SearchSeeAllMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.SearchSeeAllMoviesUseCaseImpl
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
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun bindSearchSeeAllMoviesUseCase(searchSeeAllMoviesUseCaseImpl: SearchSeeAllMoviesUseCaseImpl): SearchSeeAllMoviesUseCase

    companion object {
        @Provides
        fun provideSearchService(retrofit: Retrofit): SearchService =
            retrofit.create(SearchService::class.java)
    }
}