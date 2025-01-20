package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import androidx.compose.runtime.Composable
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.LoadingScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState

@Composable
fun BaseUiStateComposable(
    uiState: UiState<*>,
    tryAgainOnClickAction: () -> Unit,
    successScreen : @Composable () -> Unit
) {
    when (uiState) {
        is UiState.Error -> ErrorScreen(
            exception = (uiState).exception,
            tryAgainOnClickAction = tryAgainOnClickAction
        )

        UiState.Loading -> LoadingScreen()
        is UiState.Success -> successScreen()
    }
}