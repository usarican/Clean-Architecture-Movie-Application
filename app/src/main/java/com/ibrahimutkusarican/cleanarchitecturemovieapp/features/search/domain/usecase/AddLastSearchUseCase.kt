package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import kotlinx.coroutines.flow.Flow

interface AddLastSearchUseCase {
    fun addLastSearch(searchText: String) : Flow<UiState<Boolean>>
}