package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.pagingsource

import com.iusarican.common.base.BasePagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailAuthorResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.repository.datasourceImpl.DetailRemoteDataSourceImpl
import com.iusarican.common.utils.Constants.STARTING_PAGE_INDEX

class MovieReviewsSeeAllPagingSource(
    private val detailRemoteDataSource: DetailRemoteDataSourceImpl,
    private val movieId: Int,
) : BasePagingSource<MovieDetailAuthorResponse>() {
    override suspend fun executeLoad(params: LoadParams<Int>): LoadResult<Int, MovieDetailAuthorResponse> {
        val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
        val response =
            detailRemoteDataSource.getMovieReviews(movieId = movieId, page = nextPageNumber)
        return LoadResult.Page(
            data = response.authorResponses,
            prevKey = if (nextPageNumber == STARTING_PAGE_INDEX) null else nextPageNumber - 1,
            nextKey = if (response.authorResponses.isEmpty()) null else response.page + 1
        )
    }

}