package com.iusarican.domain.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    suspend fun getMovieDetailResponse(movieId : Int) : Flow<ApiState<MovieDetailInfoModel>>
    suspend fun getMovieDetailCredits(movieId : Int) : Flow<ApiState<MovieDetailCreditResponse>>
    suspend fun getMovieDetailRecommendationMovies(movieId : Int) : Flow<ApiState<MovieResponse>>
    suspend fun getMovieDetailReviews(movieId: Int) : Flow<ApiState<MovieDetailReviewResponse>>
    suspend fun getMovieDetailTrailers(movieId: Int) : Flow<ApiState<MovieDetailVideoResponse>>
}