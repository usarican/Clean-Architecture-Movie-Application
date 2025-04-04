package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase

interface ChangeUserSettingsUseCase {
    suspend fun changeDarkModePreferences(isDarkModeEnable : Boolean)
    suspend fun changeNotificationPreferences(isNotificationEnable : Boolean)
    suspend fun changeLanguagePreferences(languageCode : String)
}