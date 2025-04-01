package com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.base

import androidx.compose.runtime.Composable

import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.LoadingScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState

@Composable
fun <T> BaseUiStateComposable(
    uiState: UiState<T>,
    tryAgainOnClickAction: () -> Unit,
    backButtonClickAction: () -> Unit,
    successScreen: @Composable (data: T) -> Unit
) {
    when (uiState) {
        is UiState.Error -> ErrorScreen(
            exception = (uiState).exception,
            tryAgainOnClickAction = tryAgainOnClickAction,
            backButtonOnClickAction = backButtonClickAction,
            backButtonIsEnable = true
        )

        UiState.Loading -> LoadingScreen()
        is UiState.Success -> successScreen(uiState.data)
    }
}