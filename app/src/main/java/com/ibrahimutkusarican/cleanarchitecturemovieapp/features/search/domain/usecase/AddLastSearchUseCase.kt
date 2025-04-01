package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchItemModel
import kotlinx.coroutines.flow.Flow

interface AddLastSearchUseCase {
    fun addLastSearch(searchText: String) : Flow<UiState<List<SearchItemModel>>>
}