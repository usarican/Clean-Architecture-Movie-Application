package com.iusarican.data.di


import com.iusarican.data.repository.datasource.DetailLocalDataSource
import com.iusarican.data.repository.datasource.DetailRemoteDataSource
import com.iusarican.data.repository.datasourceImpl.DetailLocalDataSourceImpl
import com.iusarican.data.repository.datasourceImpl.DetailRemoteDataSourceImpl
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