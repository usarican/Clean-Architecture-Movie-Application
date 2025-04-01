package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import kotlinx.coroutines.flow.Flow

interface RestartAppUseCase {
    fun restartApp() : Flow<UiState<Unit>>
}