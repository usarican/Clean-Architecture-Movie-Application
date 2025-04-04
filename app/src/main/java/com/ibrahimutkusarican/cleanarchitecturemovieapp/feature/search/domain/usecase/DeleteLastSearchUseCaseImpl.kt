package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.mapper.SearchItemModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.SearchItemModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeleteLastSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val searchItemModelMapper: SearchItemModelMapper
) : BaseUseCase(), DeleteLastSearchUseCase {
    override fun deleteAllLastSearch(): Flow<UiState<List<SearchItemModel>>> {
        return execute {
            searchRepository.deleteAllSearchQueries().first().getSuccessOrThrow()
            emptyList()
        }
    }

    override fun deleteLastSearch(searchItemModel: SearchItemModel): Flow<UiState<List<SearchItemModel>>> {
        return execute {
            searchRepository.deleteSearchQuery(searchItemModelMapper.modelToEntity(searchItemModel))
                .first().getSuccessOrThrow()
            searchRepository.getLastSearchQueries().first().getSuccessOrThrow().map {
                searchItemModelMapper.entityToModel(it)
            }
        }
    }
}