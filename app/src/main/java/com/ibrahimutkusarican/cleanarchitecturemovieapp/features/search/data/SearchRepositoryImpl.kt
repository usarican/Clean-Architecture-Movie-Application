package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local.VisitedMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.SearchLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.entities.RegionEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.paging.SearchMoviePagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote.SearchRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote.responses.Region
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.MOVIE_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val searchLocalDataSource: SearchLocalDataSource,
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

    override fun getRecentlyViewedMovies(): Flow<ApiState<List<VisitedMovieEntity>>> {
        return apiCall {
            searchLocalDataSource.getLastVisitedMovies()
        }
    }

    override fun getRegions(): Flow<ApiState<List<RegionEntity>>> {
        return apiCall {
            searchLocalDataSource.getRegions().ifEmpty {
                val regionResponse = searchRemoteDataSource.getRegionsFromRemote()
                searchLocalDataSource.insertRegions(regionResponse.regions.map { response ->
                    RegionEntity(
                        regionCode = response.regionCode,
                        regionName = response.regionName
                    )
                })
                searchLocalDataSource.getRegions()
            }
        }
    }

    override fun deleteAllRegions(): Flow<ApiState<Boolean>> {
        return apiCall {
            searchLocalDataSource.deleteAllRegions()
            true
        }
    }
}
