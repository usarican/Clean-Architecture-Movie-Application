package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote.RegionsService
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote.SearchService
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.GetSearchFilterModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.GetSearchFilterModelUseCaseImpl
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

    @Binds
    abstract fun bindGetSearchFilterModelUseCase(getSearchFilterModelUseCaseImpl: GetSearchFilterModelUseCaseImpl): GetSearchFilterModelUseCase

    companion object {
        @Provides
        fun provideSearchService(retrofit: Retrofit): SearchService =
            retrofit.create(SearchService::class.java)

        @Provides
        fun provideRegionService(retrofit: Retrofit): RegionsService =
            retrofit.create(RegionsService::class.java)
    }
}