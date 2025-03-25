package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data

import android.content.Context
import android.content.res.Configuration
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.Language
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.USER_SETTINGS_DATA_STORE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Locale

class UserSettingsDataStore(
    private val context: Context
) {
    suspend fun setDarkMode(enabled: Boolean) {
        context.userSettingsDataStore.edit { prefs ->
            prefs[DARK_MODE_KEY] = enabled
        }
    }
    suspend fun setNotificationsEnabled(enabled: Boolean){
        context.userSettingsDataStore.edit { prefs ->
            prefs[NOTIFICATION_ENABLED_KEY] = enabled
        }
    }
    suspend fun setLanguage(languageCode: String){
        context.userSettingsDataStore.edit { prefs ->
            prefs[LANGUAGE_KEY] = languageCode
        }
        setLanguageChangeFlag(languageChange = true)
    }
    fun getDarkMode(): Flow<Boolean> = context.userSettingsDataStore.data.map { prefs ->
        prefs[DARK_MODE_KEY] ?: DEFAULT_DARK_MODE
    }
    fun getNotificationsEnabled(): Flow<Boolean> = context.userSettingsDataStore.data.map { prefs ->
        prefs[NOTIFICATION_ENABLED_KEY] ?: DEFAULT_NOTIFICATION_ENABLE
    }
    fun getLanguageCode(): Flow<String> = context.userSettingsDataStore.data.map { prefs ->
        prefs[LANGUAGE_KEY] ?: getCurrentLocale().languageCode
    }

    suspend fun setLanguageChangeFlag(languageChange : Boolean){
        context.userSettingsDataStore.edit { mutablePreferences ->
            mutablePreferences[LANGUAGE_CHANGE_KEY] = languageChange
        }
    }

    fun getLanguageChangeFlag() : Flow<Boolean> {
        return context.userSettingsDataStore.data.map { prefs ->
            prefs[LANGUAGE_CHANGE_KEY] ?: DEFAULT_LANGUAGE_FLAG_VALUE
        }
    }

    fun applyLocale(languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }

    private fun getCurrentLocale() : Language {
        return Language.fromLanguageCode(context.resources.configuration.getLocales().get(0).language)
    }



    companion object {
        private val Context.userSettingsDataStore by preferencesDataStore(USER_SETTINGS_DATA_STORE)
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        private val NOTIFICATION_ENABLED_KEY = booleanPreferencesKey("notification_enabled")
        private val LANGUAGE_KEY = stringPreferencesKey("language")
        private val LANGUAGE_CHANGE_KEY = booleanPreferencesKey("language_change")
        private const val DEFAULT_LANGUAGE_FLAG_VALUE = false
        private const val DEFAULT_DARK_MODE = false
        private const val DEFAULT_NOTIFICATION_ENABLE = false
    }
}