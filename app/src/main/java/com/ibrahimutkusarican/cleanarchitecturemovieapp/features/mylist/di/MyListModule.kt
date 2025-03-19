package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepositoryImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.local.MyListMovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.DeleteMyListMovieUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.DeleteMyListMovieUseCaseImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.GetMyListMovieUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.GetMyListMovieUseCaseImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.UpdateMyListMovieUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.UpdateMyListMovieUseCaseImpl
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