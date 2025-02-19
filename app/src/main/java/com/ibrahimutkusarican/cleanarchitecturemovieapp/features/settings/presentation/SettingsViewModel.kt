package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.presentation

import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.SettingsModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase.ChangeUserSettingsUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase.GetSettingsModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsModelUseCase: GetSettingsModelUseCase,
    private val changeUserSettingsUseCase: ChangeUserSettingsUseCase
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
            if(userSettings.value.selectedLanguage.languageCode == languageCode) return@launch
            changeUserSettingsUseCase.changeLanguagePreferences(languageCode)
            sendEvent(MyEvent.RestartApp)
        }

    }
}