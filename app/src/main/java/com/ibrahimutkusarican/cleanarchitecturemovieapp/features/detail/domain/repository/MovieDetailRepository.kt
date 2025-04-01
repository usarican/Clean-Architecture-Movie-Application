package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.remote.MovieDetailCreditResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.remote.MovieDetailResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.remote.MovieReviewResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.remote.MovieVideoResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    suspend fun getMovieDetailResponse(movieId : Int) : Flow<ApiState<MovieDetailResponse>>
    suspend fun getMovieDetailCredits(movieId : Int) : Flow<ApiState<MovieDetailCreditResponse>>
    suspend fun getMovieDetailRecommendationMovies(movieId : Int) : Flow<ApiState<MovieResponse>>
    suspend fun getMovieDetailReviews(movieId: Int) : Flow<ApiState<MovieReviewResponse>>
    suspend fun getMovieDetailTrailers(movieId: Int) : Flow<ApiState<MovieVideoResponse>>
}