package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ibrahimutkusarican.cleanarchitecturemovieapp.di.UserSettingsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserSettingsRepositoryImpl @Inject constructor(
    @UserSettingsDataStore private val dataStore: DataStore<Preferences>
) : UserSettingsRepository {
    override suspend fun setDarkMode(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[DARK_MODE_KEY] = enabled
        }
    }

    override suspend fun setNotificationsEnabled(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[NOTIFICATION_ENABLED_KEY] = enabled
        }
    }

    override suspend fun setLanguage(languageCode: String) {
        dataStore.edit { prefs ->
            prefs[LANGUAGE_KEY] = languageCode
        }
    }

    override fun getDarkMode(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[DARK_MODE_KEY] ?: false
        }
    }

    override fun getNotificationsEnabled(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[NOTIFICATION_ENABLED_KEY] ?: false
        }
    }

    override fun getLanguage(): Flow<String> {
        return dataStore.data.map { prefs ->
            prefs[LANGUAGE_KEY] ?: DEFAULT_LANGUAGE
        }
    }

    private companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        val NOTIFICATION_ENABLED_KEY = booleanPreferencesKey("notification_enabled")
        val LANGUAGE_KEY = stringPreferencesKey("language")
        const val DEFAULT_LANGUAGE = "en"
    }
}