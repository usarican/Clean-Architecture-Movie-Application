package com.ibrahimutkusarican.cleanarchitecturemovieapp.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data.UserSettingsDataStore
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.MOVIE_API_URL
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.TIME_OUT_VALUE
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.RequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
       userSettingsDataStore: UserSettingsDataStore
    ) : OkHttpClient {
        return OkHttpClient().newBuilder()
            .addNetworkInterceptor(RequestInterceptor(userSettingsDataStore))
            .addInterceptor(HttpLoggingInterceptor())
            .readTimeout(TIME_OUT_VALUE, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_VALUE,TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        movieAppUrl : String,
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ) : Retrofit =
        Retrofit.Builder()
            .baseUrl(movieAppUrl)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Singleton
    @Provides
    fun provideMovieApiUrl() : String = MOVIE_API_URL

    @Singleton
    @Provides
    fun provideMoshi() : Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi) : MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }
}