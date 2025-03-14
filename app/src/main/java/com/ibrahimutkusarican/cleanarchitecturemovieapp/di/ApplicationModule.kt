package com.ibrahimutkusarican.cleanarchitecturemovieapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageShareHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.LocaleManager
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

    @Provides
    @Singleton
    @UserSettingsDataStore
    fun providePreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile("USER_SETTINGS_DATASTORE_NAME")
            }
        )
    }

    @Singleton
    @Provides
    fun provideLocaleManager(
        @ApplicationContext context: Context
    ) : LocaleManager = LocaleManager(context)

    @Singleton
    @Provides
    fun provideImageShareHelper(
        @ApplicationContext context: Context
    ) : ImageShareHelper = ImageShareHelper(context)
}