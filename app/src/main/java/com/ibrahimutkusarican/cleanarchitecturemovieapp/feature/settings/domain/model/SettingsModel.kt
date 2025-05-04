package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.model

import com.iusarican.Language

data class SettingsModel(
    val isDarkModeEnabled: Boolean = false,
    val selectedLanguage: Language = Language.EN,
    val isNotificationEnabled: Boolean = false
)

