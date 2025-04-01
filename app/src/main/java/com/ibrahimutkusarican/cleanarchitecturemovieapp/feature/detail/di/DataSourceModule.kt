package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.di

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.repository.datasource.DetailLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.repository.datasource.DetailRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.repository.datasourceImpl.DetailLocalDataSourceImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.repository.datasourceImpl.DetailRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindDetailLocalDataSource(
        detailLocalDataSourceImpl: DetailLocalDataSourceImpl
    ): DetailLocalDataSource

    @Binds
    abstract fun bindDetailRemoteDataSource(
        detailRemoteDataSourceImpl: DetailRemoteDataSourceImpl
    ): DetailRemoteDataSource

}