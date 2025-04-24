package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailAuthorResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.repository.datasourceImpl.DetailRemoteDataSourceImpl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.MovieLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.MovieRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.pagingsource.MovieReviewsSeeAllPagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.pagingsource.RecommendedMovieSeeAllPagingSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.pagingsource.SeeAllMoviePagingSource
import com.iusarican.common.utils.Constants.MOVIE_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeeAllRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieEntityToResponseMapper: MovieEntityToResponseMapper,
    private val detailRemoteDataSource: DetailRemoteDataSourceImpl
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

    override fun getMovieReviewsSeeAll(movieId: Int): Flow<PagingData<MovieDetailAuthorResponse>> {
        return Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE), pagingSourceFactory = {
            MovieReviewsSeeAllPagingSource(
                movieId = movieId,
                detailRemoteDataSource = detailRemoteDataSource
            )
        }).flow
    }
}