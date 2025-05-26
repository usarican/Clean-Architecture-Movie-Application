package com.iusarican.data.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.iusarican.common.base.BaseRepository
import com.iusarican.data.mapper.MovieDetailResponseMapper
import com.iusarican.data.model.remote.MovieDetailCreditResponse
import com.iusarican.data.model.remote.MovieDetailReviewResponse
import com.iusarican.domain.repository.MovieDetailRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResponse
import com.iusarican.data.datasource.DetailLocalDataSource
import com.iusarican.data.datasource.DetailRemoteDataSource
import com.iusarican.data.mapper.MovieDetailModelMapper
import com.iusarican.data.model.remote.MovieDetailVideoResponse
import com.iusarican.domain.model.MovieDetailInfoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val detailRemoteDataSource: DetailRemoteDataSource,
    private val detailLocalDataSource: DetailLocalDataSource,
    private val movieDetailResponseMapper: MovieDetailResponseMapper,
    private val movieDetailModelMapper: MovieDetailModelMapper
) : BaseRepository(), MovieDetailRepository {
    override suspend fun getMovieDetailResponse(movieId: Int): Flow<ApiState<MovieDetailInfoModel>> {
        return apiCall {
            val movieDetailResponse = detailRemoteDataSource.getMovieDetail(movieId)
            val visitedMovieEntity = movieDetailResponseMapper.mapResponseToEntity(movieDetailResponse)
            detailLocalDataSource.insertVisitedMovie(visitedMovieEntity)

            movieDetailModelMapper.movieDetailResponseToMovieDetailInfoModel(movieDetailResponse)
        }
    }

    override suspend fun getMovieDetailCredits(movieId: Int): Flow<ApiState<MovieDetailCreditResponse>> {
        return apiCall { detailRemoteDataSource.getMovieCredits(movieId) }
    }

    override suspend fun getMovieDetailRecommendationMovies(movieId: Int): Flow<ApiState<MovieResponse>> {
        return apiCall { detailRemoteDataSource.getMovieRecommendations(movieId) }
    }

    override suspend fun getMovieDetailReviews(movieId: Int): Flow<ApiState<MovieDetailReviewResponse>> {
        return apiCall { detailRemoteDataSource.getMovieReviews(movieId) }
    }

    override suspend fun getMovieDetailTrailers(movieId: Int): Flow<ApiState<MovieDetailVideoResponse>> {
        return apiCall { detailRemoteDataSource.getMovieTrailers(movieId) }
    }

}