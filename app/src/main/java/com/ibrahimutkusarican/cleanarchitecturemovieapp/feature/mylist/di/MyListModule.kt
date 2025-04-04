package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.database.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data.MyListRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data.local.MyListMovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.domain.usecase.GetMyListMovieUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.domain.usecase.GetMyListMovieUseCaseImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.domain.usecase.UpdateMyListMovieUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.domain.usecase.UpdateMyListMovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MyListModule {
    @Binds
    abstract fun bindMyListRepository(myListRepositoryImpl: MyListRepositoryImpl): MyListRepository

    @Binds
    abstract fun bindGetMyListMovieUseCase(getMyListMovieUseCaseImpl: GetMyListMovieUseCaseImpl): GetMyListMovieUseCase

    @Binds
    abstract fun bindUpdateMyListMovieUseCase(updateMyListMovieUseCaseImpl: UpdateMyListMovieUseCaseImpl) : UpdateMyListMovieUseCase

    companion object {
        @Provides
        fun provideMyListMovieDao(movieDatabase: MovieDatabase): MyListMovieDao =
            movieDatabase.myListMovieDao()
    }
}