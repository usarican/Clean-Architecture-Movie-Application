package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local.VisitedMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.entities.LastSearchEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.entities.RegionEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchMovies(searchText: String): Flow<PagingData<MovieResultResponse>>
    fun filterMovies(
        releaseYear: Int?,
        sortBy: String,
        genre: String?,
        region: String?
    ): Flow<PagingData<MovieResultResponse>>

    fun getRecommendedMovieById(movieId: Int): Flow<ApiState<List<MovieResultResponse>>>
    fun getRecentlyViewedMovies(): Flow<ApiState<List<VisitedMovieEntity>>>
    fun getRegions(): Flow<ApiState<List<RegionEntity>>>
    fun deleteAllRegions(): Flow<ApiState<Boolean>>
    fun insertNewSearchQuery(lastSearchEntity: LastSearchEntity): Flow<ApiState<Boolean>>
    fun deleteAllSearchQueries(): Flow<ApiState<Boolean>>
    fun deleteSearchQuery(lastSearchEntity: LastSearchEntity): Flow<ApiState<Boolean>>
    fun getLastSearchQueries(): Flow<ApiState<List<LastSearchEntity>>>

}