package com.iusarican.data.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.repository.MovieDetailRepository
import com.iusarican.data.repository.MovieDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieDetailRepository(movieDetailRepositoryImpl: MovieDetailRepositoryImpl): MovieDetailRepository

}
