package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.presentation

import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.iusarican.common.base.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.model.SettingsModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase.ChangeUserSettingsUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase.GetSettingsModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase.RestartAppUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsModelUseCase: GetSettingsModelUseCase,
    private val changeUserSettingsUseCase: ChangeUserSettingsUseCase,
    private val restartAppUseCase: RestartAppUseCase
) : BaseViewModel() {

    val userSettings = getSettingsModelUseCase.getSettingsModel()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsModel())

    fun handleUiAction(action: SettingsUiAction) {
        when (action) {
            is SettingsUiAction.ChangeDarkMode -> changeDarkMode(action.isDarkModeEnable)
            is SettingsUiAction.ChangeLanguage -> changeLanguage(action.languageCode)
            is SettingsUiAction.ChangeNotification -> changeNotification(action.isNotificationEnable)
            SettingsUiAction.ClickAboutMe -> TODO()
            SettingsUiAction.ClickHelpCenter -> TODO()
        }
    }

    private fun changeDarkMode(isDarkModeEnable: Boolean) {
        viewModelScope.launch {
            changeUserSettingsUseCase.changeDarkModePreferences(isDarkModeEnable)
        }
    }

    private fun changeNotification(isNotificationEnable: Boolean) {
        viewModelScope.launch {
            changeUserSettingsUseCase.changeNotificationPreferences(isNotificationEnable)
        }
    }

    private fun changeLanguage(languageCode: String) {
        viewModelScope.launch {
            if (userSettings.value.selectedLanguage.languageCode == languageCode) return@launch
            changeUserSettingsUseCase.changeLanguagePreferences(languageCode)
            restartAppUseCase.restartApp().doOnSuccess { sendEvent(MyEvent.RestartApp) }.collect()
        }

    }
}