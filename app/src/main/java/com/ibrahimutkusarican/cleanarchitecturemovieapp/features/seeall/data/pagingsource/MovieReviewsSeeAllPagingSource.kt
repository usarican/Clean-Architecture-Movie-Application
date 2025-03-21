package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.pagingsource

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BasePagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.AuthorResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.DetailRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.STARTING_PAGE_INDEX

class MovieReviewsSeeAllPagingSource(
    private val detailRemoteDataSource: DetailRemoteDataSource,
    private val movieId: Int,
) : BasePagingSource<AuthorResponse>() {
    override suspend fun executeLoad(params: LoadParams<Int>): LoadResult<Int, AuthorResponse> {
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