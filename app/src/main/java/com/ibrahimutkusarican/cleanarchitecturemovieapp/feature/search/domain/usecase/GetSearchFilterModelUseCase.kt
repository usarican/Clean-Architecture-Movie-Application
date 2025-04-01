package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.SearchFilterModel
import kotlinx.coroutines.flow.Flow

interface GetSearchFilterModelUseCase {
    fun getSearchFilterModel() : Flow<UiState<SearchFilterModel>>
}