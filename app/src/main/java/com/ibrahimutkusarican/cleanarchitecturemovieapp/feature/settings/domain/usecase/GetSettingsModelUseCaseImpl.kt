package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.data.UserSettingsRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.model.SettingsModel
import com.iusarican.Language
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
            userSettingsRepository.getLanguageCode()
        ) { darkMode, notificationEnabled, language ->
            SettingsModel(
                isDarkModeEnabled = darkMode,
                isNotificationEnabled = notificationEnabled,
                selectedLanguage = Language.fromLanguageCode(language)
            )
        }
    }
}