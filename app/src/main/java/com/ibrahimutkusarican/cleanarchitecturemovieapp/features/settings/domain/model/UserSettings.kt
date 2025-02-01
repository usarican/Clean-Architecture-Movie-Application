package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R

enum class UserSettings(
    @StringRes val text: Int,
    @DrawableRes val icon: Int,
    val settingsType: SettingsType
) {
    DARK_MODE(
        text = R.string.dark_mode,
        icon = R.drawable.ic_dark_mode,
        settingsType = SettingsType.SWITCH
    ),
    LANGUAGE(
        text = R.string.language,
        icon = R.drawable.ic_language,
        settingsType = SettingsType.TEXT_AND_CLICK
    ),
    NOTIFICATION(
        text = R.string.notification,
        icon = R.drawable.ic_notifications,
        settingsType = SettingsType.CLICK
    ),
    ABOUT_ME(
        text = R.string.about_me,
        icon = R.drawable.ic_info,
        settingsType = SettingsType.CLICK
    ),
    HELP_CENTER(
        text = R.string.help_center,
        icon = R.drawable.ic_help_center,
        settingsType = SettingsType.CLICK
    )
}

enum class SettingsType {
    SWITCH, TEXT_AND_CLICK, CLICK
}
