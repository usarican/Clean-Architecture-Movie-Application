package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.data.UserSettingsRepository
import javax.inject.Inject

class ChangeUserSettingsUseCaseImpl @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository
) : ChangeUserSettingsUseCase  {
    override suspend fun changeDarkModePreferences(isDarkModeEnable: Boolean) {
        userSettingsRepository.setDarkMode(isDarkModeEnable)
    }

    override suspend fun changeNotificationPreferences(isNotificationEnable: Boolean) {
        userSettingsRepository.setNotificationsEnabled(isNotificationEnable)
    }

    override suspend fun changeLanguagePreferences(languageCode: String) {
        userSettingsRepository.setLanguage(languageCode)
    }

}