package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import kotlinx.coroutines.flow.Flow

interface SearchSeeAllMoviesUseCase {
    fun searchSeeAllMovies(searchText : String): Flow<PagingData<SeeAllMovieModel>>
}