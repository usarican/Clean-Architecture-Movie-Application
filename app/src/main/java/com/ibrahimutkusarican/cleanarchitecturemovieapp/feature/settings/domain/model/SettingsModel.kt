package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.model

import androidx.annotation.StringRes
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R

data class SettingsModel(
    val isDarkModeEnabled: Boolean = false,
    val selectedLanguage: Language = Language.EN,
    val isNotificationEnabled: Boolean = false
)

enum class Language(
    val languageCode: String,
    @StringRes val languageText: Int
) {
    TR(languageCode = "tr", languageText = R.string.language_tr),
    EN(languageCode = "en", languageText = R.string.language_en);

    companion object {
        fun fromLanguageCode(languageCode: String): Language {
            return entries.find { it.languageCode == languageCode } ?: EN
        }
    }
}
