package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.network.MovieDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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