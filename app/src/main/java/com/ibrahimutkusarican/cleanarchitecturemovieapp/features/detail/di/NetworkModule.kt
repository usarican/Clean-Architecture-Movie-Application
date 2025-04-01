package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.remote.MovieDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMovieDetailService(
        retrofit: Retrofit
    ): MovieDetailService {
        return retrofit.create(MovieDetailService::class.java)
    }
}