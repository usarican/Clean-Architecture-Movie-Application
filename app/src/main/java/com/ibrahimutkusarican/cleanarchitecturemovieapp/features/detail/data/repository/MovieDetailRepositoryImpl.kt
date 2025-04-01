package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.mapper.MovieDetailResponseMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.model.remote.MovieDetailCreditResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.model.remote.MovieDetailResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.model.remote.MovieDetailReviewResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.model.remote.MovieDetailVideoResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.repository.datasource.DetailLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.repository.datasource.DetailRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.repository.MovieDetailRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val detailRemoteDataSource: DetailRemoteDataSource,
    private val detailLocalDataSource: DetailLocalDataSource,
    private val movieDetailResponseMapper: MovieDetailResponseMapper
) :BaseRepository(), MovieDetailRepository {
    override suspend fun getMovieDetailResponse(movieId: Int): Flow<ApiState<MovieDetailResponse>> {
        return apiCall {
            val movieDetailResponse = detailRemoteDataSource.getMovieDetail(movieId)
            val visitedMovieEntity = movieDetailResponseMapper.mapResponseToEntity(movieDetailResponse)
            detailLocalDataSource.insertVisitedMovie(visitedMovieEntity)
            movieDetailResponse
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