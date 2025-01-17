package com.ibrahimutkusarican.cleanarchitecturemovieapp.di

import android.content.Context
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideStringProvider(
        @ApplicationContext context: Context
    ): StringProvider = StringProvider(context)
}