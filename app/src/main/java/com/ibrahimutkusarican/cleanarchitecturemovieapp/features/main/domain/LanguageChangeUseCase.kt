package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.domain

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import kotlinx.coroutines.flow.Flow

interface LanguageChangeUseCase {
    fun languageChangeGenreAndHomeMovies() : Flow<UiState<Boolean>>
    fun languageChangeForMyListMovies() : Flow<UiState<Boolean>>
}