package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.paging

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.BasePagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote.SearchRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.STARTING_PAGE_INDEX

class SearchMoviePagingSource(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val searchText: String,
) : BasePagingSource<MovieResultResponse>() {
    override suspend fun executeLoad(params: LoadParams<Int>): LoadResult<Int, MovieResultResponse> {
        val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
        val movieResponse = searchRemoteDataSource.searchMovies(searchText, nextPageNumber)
        return LoadResult.Page(
            data = movieResponse.movieResultResponse,
            prevKey = if (nextPageNumber == STARTING_PAGE_INDEX) null else nextPageNumber - 1,
            nextKey = if (movieResponse.movieResultResponse.isEmpty()) null else movieResponse.page + 1
        )
    }
}