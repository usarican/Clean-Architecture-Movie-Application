package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.usecase

import com.iusarican.common.base.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.mapper.SearchItemModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.SearchItemModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AddLastSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val searchItemModelMapper: SearchItemModelMapper
) : BaseUseCase(), AddLastSearchUseCase {
    override fun addLastSearch(searchText: String): Flow<UiState<List<SearchItemModel>>> {
        return execute {
            searchRepository.insertNewSearchQuery(
                searchItemModelMapper.searchTextToEntity(
                    searchText
                )
            ).first().getSuccessOrThrow()
            searchRepository.getLastSearchQueries().first().getSuccessOrThrow().map {
                searchItemModelMapper.entityToModel(it)
            }
        }
    }
}