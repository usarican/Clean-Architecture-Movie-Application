package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.domain.model.ExploreInitialDataModel
import kotlinx.coroutines.flow.Flow

interface GetExploreInitialDataUseCase {
    fun getExploreInitialData() : Flow<UiState<ExploreInitialDataModel>>
}