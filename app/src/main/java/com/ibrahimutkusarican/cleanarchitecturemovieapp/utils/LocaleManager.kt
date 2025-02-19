package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import android.content.Context
import android.content.res.Configuration
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Locale

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "USER_LANGUAGE_SELECTION_DATA_STORE")
class LocaleManager(
    private val context : Context
) {

    suspend fun setLanguageChangeFlag(languageChange : Boolean){
        context.applicationContext.dataStore.edit { mutablePreferences ->
            mutablePreferences[LANGUAGE_CHANGE_KEY] = languageChange
        }
    }

    fun getLanguageChangeFlag() : Flow<Boolean> {
        return context.applicationContext.dataStore.data.map { prefs ->
            prefs[LANGUAGE_CHANGE_KEY] ?: false
        }
    }

    fun getStoredLanguage(): Flow<String> {
        return context.applicationContext.dataStore.data.map { prefs ->
            prefs[LANGUAGE_KEY] ?: DEFAULT_LANGUAGE
        }
    }

    suspend fun saveSelectedLanguage(languageCode : String){
        context.applicationContext.dataStore.edit { mutablePreferences ->
            mutablePreferences[LANGUAGE_KEY] = languageCode
        }
    }

    // Apply locale globally
    fun applyLocale(languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        val newContext = context.createConfigurationContext(config)
        return newContext
    }

    companion object {
        private const val DEFAULT_LANGUAGE = "tr"
        private val LANGUAGE_KEY = stringPreferencesKey("language")
        private val LANGUAGE_CHANGE_KEY = booleanPreferencesKey("language_change")
    }
}