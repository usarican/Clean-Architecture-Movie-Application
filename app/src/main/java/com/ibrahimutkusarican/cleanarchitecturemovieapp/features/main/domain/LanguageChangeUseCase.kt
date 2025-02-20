package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.domain

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import kotlinx.coroutines.flow.Flow

interface LanguageChangeUseCase {
    fun languageChangeForHomeMovies() : Flow<UiState<Boolean>>
    fun languageChangeForMyListMovies() : Flow<UiState<Boolean>>
    fun languageChangeForGenre() : Flow<UiState<Boolean>>
}