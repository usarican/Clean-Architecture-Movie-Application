package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchScreenModel
import kotlinx.coroutines.flow.Flow

interface GetSearchScreenModelUseCase {
    fun getScreenModelUseCase(movieId : Int?) : Flow<UiState<SearchScreenModel>>
}