package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.local.VisitedMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local.entities.LastSearchEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local.entities.RegionEntity
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