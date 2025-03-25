package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSettingsRepositoryImpl @Inject constructor(
    private val userSettingsDataStore: UserSettingsDataStore
) : UserSettingsRepository {
    override suspend fun setDarkMode(enabled: Boolean) {
        userSettingsDataStore.setDarkMode(enabled)
    }

    override suspend fun setNotificationsEnabled(enabled: Boolean) {
        userSettingsDataStore.setNotificationsEnabled(enabled)
    }

    override suspend fun setLanguage(languageCode: String) {
        userSettingsDataStore.setLanguage(languageCode)
    }

    override fun getDarkMode(): Flow<Boolean> {
        return userSettingsDataStore.getDarkMode()
    }

    override fun getNotificationsEnabled(): Flow<Boolean> {
        return userSettingsDataStore.getNotificationsEnabled()
    }

    override fun getLanguageCode(): Flow<String> {
        return userSettingsDataStore.getLanguageCode()
    }
}