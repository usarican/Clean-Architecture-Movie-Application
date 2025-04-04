package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.SearchItemModel
import kotlinx.coroutines.flow.Flow

interface DeleteLastSearchUseCase {
    fun deleteAllLastSearch() : Flow<UiState<List<SearchItemModel>>>
    fun deleteLastSearch(searchItemModel: SearchItemModel) : Flow<UiState<List<SearchItemModel>>>
}