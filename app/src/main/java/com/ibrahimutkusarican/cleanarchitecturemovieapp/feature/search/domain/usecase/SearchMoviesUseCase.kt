package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.usecase

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.model.SeeAllMovieModel
import kotlinx.coroutines.flow.Flow

interface SearchMoviesUseCase {
    fun searchSeeAllMovies(searchText : String): Flow<PagingData<SeeAllMovieModel>>
}