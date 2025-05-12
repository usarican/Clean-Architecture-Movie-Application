package com.iusarican.data.di

import com.iusarican.data.remote.GenreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGenreService(retrofit: Retrofit): GenreService =
        retrofit.create(GenreService::class.java)

}