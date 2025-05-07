package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.pagingsource

import com.iusarican.common.base.BasePagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.MovieLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.MovieRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.MovieEntityToResponseMapper
import com.iusarican.common.utils.Constants.STARTING_PAGE_INDEX

class SeeAllMoviePagingSource(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieType: MovieType,
    private val entityToResponseMapper: MovieEntityToResponseMapper
) : BasePagingSource<MovieResultResponse>() {

    override suspend fun executeLoad(params: LoadParams<Int>): LoadResult<Int, MovieResultResponse> {
        val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
        var responsePage: Int? = null

        val data = if (nextPageNumber == STARTING_PAGE_INDEX) {
            val movieEntitiesByType = movieLocalDataSource.getMoviesByType(movieType)
            movieEntitiesByType.map { entityToResponseMapper.entityToResponse(it) }
        } else {
            val movieResponse = when (movieType) {
                MovieType.NOW_PLAYING -> movieRemoteDataSource.getNowPlayingMovies(
                    nextPageNumber
                )

                MovieType.POPULAR -> movieRemoteDataSource.getPopularMovies(nextPageNumber)
                MovieType.TOP_RATED -> movieRemoteDataSource.getTopRatedMovies(nextPageNumber)
                MovieType.UPCOMING -> movieRemoteDataSource.getUpComingMovies(nextPageNumber)
            }
            responsePage = movieResponse.page
            movieResponse.movieResultResponse
        }

        return LoadResult.Page(
            data = data,
            prevKey = if (nextPageNumber == STARTING_PAGE_INDEX) null else nextPageNumber - 1,
            nextKey = if (data.isEmpty()) null else (responsePage ?: STARTING_PAGE_INDEX) + 1
        )
    }

}