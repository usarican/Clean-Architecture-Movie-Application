package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.mapper.SearchItemModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AddLastSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val searchItemModelMapper: SearchItemModelMapper
) : BaseUseCase(), AddLastSearchUseCase {
    override fun addLastSearch(searchText: String): Flow<UiState<Boolean>> {
        return execute {
            searchRepository.insertNewSearchQuery(
                searchItemModelMapper.searchTextToEntity(
                    searchText
                )
            ).first().getSuccessOrThrow()
        }
    }
}