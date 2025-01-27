package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.local.MyListMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MyListModule {
    companion object {
        @Provides
        fun provideMyListMovieDao(movieDatabase: MovieDatabase): MyListMovieDao =
            movieDatabase.myListMovieDao()
    }
}