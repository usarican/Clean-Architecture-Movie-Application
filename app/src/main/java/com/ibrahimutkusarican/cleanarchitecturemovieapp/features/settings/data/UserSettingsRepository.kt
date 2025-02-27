package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data

import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {
    suspend fun setDarkMode(enabled: Boolean)
    suspend fun setNotificationsEnabled(enabled: Boolean)
    suspend fun setLanguage(languageCode: String)
    fun getDarkMode(): Flow<Boolean>
    fun getNotificationsEnabled(): Flow<Boolean>
    fun getLanguageCode(): Flow<String>
}