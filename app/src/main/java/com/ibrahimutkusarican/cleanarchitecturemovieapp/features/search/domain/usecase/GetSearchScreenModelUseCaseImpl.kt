package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchScreenModelUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetSearchScreenModelUseCase {
    override fun getScreenModelUseCase(): Flow<UiState<SearchMoviesUseCase>> {
        TODO("Not yet implemented")
    }
}