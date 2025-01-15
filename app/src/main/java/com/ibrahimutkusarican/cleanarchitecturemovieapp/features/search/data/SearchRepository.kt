package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchMovies(searchText: String): Flow<PagingData<MovieResultResponse>>
}