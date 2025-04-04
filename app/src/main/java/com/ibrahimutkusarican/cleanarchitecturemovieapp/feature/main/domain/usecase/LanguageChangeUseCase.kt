package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.main.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import kotlinx.coroutines.flow.Flow

interface LanguageChangeUseCase {
    fun languageChangeForMyListMovies() : Flow<UiState<Boolean>>
}