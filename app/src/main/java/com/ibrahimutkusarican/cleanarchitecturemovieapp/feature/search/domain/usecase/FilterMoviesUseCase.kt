package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.usecase

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.SearchFilterModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.model.SeeAllMovieModel
import kotlinx.coroutines.flow.Flow

interface FilterMoviesUseCase {
    fun filterMovies(
        searchFilterModel: SearchFilterModel
    ): Flow<PagingData<SeeAllMovieModel>>
}