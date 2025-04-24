package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.paging

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.BasePagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.remote.SearchRemoteDataSource
import com.iusarican.common.utils.Constants.STARTING_PAGE_INDEX

class FilterMoviePagingSource(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val releaseYear : Int?,
    private val sortBy : String,
    private val genre : String?,
    private val region : String?
) : BasePagingSource<MovieResultResponse>() {
    override suspend fun executeLoad(params: LoadParams<Int>): LoadResult<Int, MovieResultResponse> {
        val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
        val movieResponse = searchRemoteDataSource.sortOrFilterMovies(
            page = nextPageNumber,
            releaseYear = releaseYear,
            sortBy = sortBy,
            genre = genre,
            region = region
        )
        return LoadResult.Page(
            data = movieResponse.movieResultResponse,
            prevKey = if (nextPageNumber == STARTING_PAGE_INDEX) null else nextPageNumber - 1,
            nextKey = if (movieResponse.movieResultResponse.isEmpty()) null else movieResponse.page + 1
        )
    }
}