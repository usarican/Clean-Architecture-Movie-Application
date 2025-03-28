package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.AuthorResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.DetailRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.MovieLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.MovieRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.pagingsource.MovieReviewsSeeAllPagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.pagingsource.RecommendedMovieSeeAllPagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.pagingsource.SeeAllMoviePagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.MOVIE_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeeAllRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieEntityToResponseMapper: MovieEntityToResponseMapper,
    private val detailRemoteDataSource: DetailRemoteDataSource
) : SeeAllRepository, BaseRepository() {

    override fun getSeeAllMoviesByType(movieType: MovieType): Flow<PagingData<MovieResultResponse>> {
        return Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE), pagingSourceFactory = {
            SeeAllMoviePagingSource(
                movieRemoteDataSource = movieRemoteDataSource,
                movieType = movieType,
                movieLocalDataSource = movieLocalDataSource,
                entityToResponseMapper = movieEntityToResponseMapper
            )
        }).flow
    }

    override fun getRecommendedSeeAllMovies(movieId: Int): Flow<PagingData<MovieResultResponse>> {
        return Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE), pagingSourceFactory = {
            RecommendedMovieSeeAllPagingSource(
                movieId = movieId,
                detailRemoteDataSource = detailRemoteDataSource
            )
        }).flow
    }

    override fun getMovieReviewsSeeAll(movieId: Int): Flow<PagingData<AuthorResponse>> {
        return Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE), pagingSourceFactory = {
            MovieReviewsSeeAllPagingSource(
                movieId = movieId,
                detailRemoteDataSource = detailRemoteDataSource
            )
        }).flow
    }
}