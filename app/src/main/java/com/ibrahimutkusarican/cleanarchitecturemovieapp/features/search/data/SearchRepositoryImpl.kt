package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.LastVisitedMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.SearchLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.paging.SearchMoviePagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote.SearchRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.MOVIE_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val searchLocalDataSource: SearchLocalDataSource
) : SearchRepository,BaseRepository() {
    override fun searchMovies(searchText: String): Flow<PagingData<MovieResultResponse>> {
        return Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE), pagingSourceFactory = {
            SearchMoviePagingSource(
                searchRemoteDataSource = searchRemoteDataSource, searchText = searchText
            )
        }).flow
    }

    override fun getRecommendedMovieById(movieId: Int): Flow<ApiState<List<MovieResultResponse>>> {
        return apiCall {
            searchRemoteDataSource.getRecommendedMovieById(movieId).movieResultResponse
        }
    }

    override fun getRecentlyViewedMovies(): Flow<ApiState<List<LastVisitedMovieEntity>>> {
        return apiCall {
            searchLocalDataSource.getLastVisitedMovies()
        }
    }
}
