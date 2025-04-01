package com.ibrahimutkusarican.cleanarchitecturemovieapp.di

import android.content.Context
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data.UserSettingsDataStore
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper.ImageShareHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.StringProvider
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
    fun provideStringProvider(
        @ApplicationContext context: Context
    ): StringProvider = StringProvider(context)


    @Singleton
    @Provides
    fun provideUserSettingsDataStore(
        @ApplicationContext context: Context
    ) : UserSettingsDataStore = UserSettingsDataStore(context)

    @Singleton
    @Provides
    fun provideImageShareHelper(
        @ApplicationContext context: Context
    ) : ImageShareHelper = ImageShareHelper(context)
}