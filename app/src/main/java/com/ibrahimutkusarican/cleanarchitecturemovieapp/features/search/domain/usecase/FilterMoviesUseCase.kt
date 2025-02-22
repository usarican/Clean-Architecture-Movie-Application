package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchFilterModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import kotlinx.coroutines.flow.Flow

interface FilterMoviesUseCase {
    fun filterMovies(
        searchText : String? = null,
        searchFilterModel: SearchFilterModel
    ): Flow<PagingData<SeeAllMovieModel>>
}