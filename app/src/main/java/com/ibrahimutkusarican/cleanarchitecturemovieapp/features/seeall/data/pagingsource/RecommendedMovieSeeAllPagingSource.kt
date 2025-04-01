package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.pagingsource

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.BasePagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.repository.datasourceImpl.DetailRemoteDataSourceImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.STARTING_PAGE_INDEX

class RecommendedMovieSeeAllPagingSource(
    private val detailRemoteDataSource: DetailRemoteDataSourceImpl,
    private val movieId: Int,
) : BasePagingSource<MovieResultResponse>() {
    override suspend fun executeLoad(params: LoadParams<Int>): LoadResult<Int, MovieResultResponse> {
        val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
        val response =
            detailRemoteDataSource.getMovieRecommendations(movieId = movieId, page = nextPageNumber)
        return LoadResult.Page(
            data = response.movieResultResponse,
            prevKey = if (nextPageNumber == STARTING_PAGE_INDEX) null else nextPageNumber - 1,
            nextKey = if (response.movieResultResponse.isEmpty()) null else response.page + 1
        )

    }

}
