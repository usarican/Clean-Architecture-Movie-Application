package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchFilterModel
import kotlinx.coroutines.flow.Flow

interface GetSearchFilterModelUseCase {
    fun getSearchFilterModel() : Flow<UiState<SearchFilterModel>>
}