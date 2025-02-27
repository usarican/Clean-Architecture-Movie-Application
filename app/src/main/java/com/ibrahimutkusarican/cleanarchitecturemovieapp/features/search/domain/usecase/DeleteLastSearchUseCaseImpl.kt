package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.mapper.SearchItemModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchItemModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeleteLastSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val searchItemModelMapper: SearchItemModelMapper
) : BaseUseCase(), DeleteLastSearchUseCase {
    override fun deleteAllLastSearch(): Flow<UiState<Boolean>> {
        return execute {
            searchRepository.deleteAllSearchQueries().first().getSuccessOrThrow()
        }
    }

    override fun deleteLastSearch(searchItemModel: SearchItemModel): Flow<UiState<Boolean>> {
        return execute {
            searchRepository.deleteSearchQuery(searchItemModelMapper.modelToEntity(searchItemModel))
                .first().getSuccessOrThrow()
        }
    }
}