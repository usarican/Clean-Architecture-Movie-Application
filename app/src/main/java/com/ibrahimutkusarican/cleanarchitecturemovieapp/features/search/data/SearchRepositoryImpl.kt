package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.MOVIE_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
) : SearchRepository {
    override fun searchMovies(searchText: String): Flow<PagingData<MovieResultResponse>> {
        return Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE), pagingSourceFactory = {
            SearchMoviePagingSource(
                searchRemoteDataSource = searchRemoteDataSource, searchText = searchText
            )
        }).flow
    }
}
