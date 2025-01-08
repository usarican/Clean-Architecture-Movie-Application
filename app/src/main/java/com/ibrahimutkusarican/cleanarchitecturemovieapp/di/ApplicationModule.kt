package com.ibrahimutkusarican.cleanarchitecturemovieapp.di

import android.content.Context
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.CoilHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideCoilHelper(@ApplicationContext context: Context): CoilHelper {
        return CoilHelper(context)
    }

}