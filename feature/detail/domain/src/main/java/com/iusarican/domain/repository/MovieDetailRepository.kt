package com.iusarican.domain.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.iusarican.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResponse
import com.iusarican.domain.model.MovieDetailCastModel
import com.iusarican.domain.model.MovieDetailReviewModel
import com.iusarican.domain.model.MovieDetailTrailerModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    suspend fun getMovieDetailResponse(movieId : Int) : Flow<ApiState<MovieDetailInfoModel>>
    suspend fun getMovieDetailCredits(movieId : Int) : Flow<ApiState<MovieDetailCastModel>>
    suspend fun getMovieDetailRecommendationMovies(movieId : Int) : Flow<ApiState<MovieResponse>>
    suspend fun getMovieDetailReviews(movieId: Int) : Flow<ApiState<MovieDetailReviewModel>>
    suspend fun getMovieDetailTrailers(movieId: Int) : Flow<ApiState<MovieDetailTrailerModel>>
}