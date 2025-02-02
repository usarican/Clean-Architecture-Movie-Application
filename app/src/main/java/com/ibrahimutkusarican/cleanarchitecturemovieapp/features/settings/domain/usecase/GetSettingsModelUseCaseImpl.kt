package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data.UserSettingsRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.Language
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.SettingsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetSettingsModelUseCaseImpl @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository
) : GetSettingsModelUseCase {
    override fun getSettingsModel(): Flow<SettingsModel> {
        return combine(
            userSettingsRepository.getDarkMode(),
            userSettingsRepository.getNotificationsEnabled(),
            userSettingsRepository.getLanguage()
        ) { darkMode, notificationEnabled, language ->
            SettingsModel(
                isDarkModeEnabled = darkMode,
                isNotificationEnabled = notificationEnabled,
                selectedLanguage = Language.fromLanguageCode(language)
            )
        }
    }
}