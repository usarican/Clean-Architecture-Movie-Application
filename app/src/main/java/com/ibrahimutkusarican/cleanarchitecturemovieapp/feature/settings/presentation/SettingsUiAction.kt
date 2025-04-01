package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.presentation

sealed class SettingsUiAction {
    data class ChangeDarkMode(val isDarkModeEnable : Boolean) : SettingsUiAction()
    data class ChangeNotification(val isNotificationEnable : Boolean) : SettingsUiAction()
    data class ChangeLanguage(val languageCode : String) : SettingsUiAction()
    data object ClickAboutMe : SettingsUiAction()
    data object ClickHelpCenter : SettingsUiAction()
}