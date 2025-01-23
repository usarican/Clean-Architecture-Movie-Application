package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetailByMovieId(@Path("movie_id") movieId: Int): MovieDetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCreditsByMovieId(@Path("movie_id") movieId: Int): MovieDetailCreditResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendationsByMovieId(@Path("movie_id") movieId: Int): MovieResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviewsByMovieId(@Path("movie_id") movieId: Int): MovieReviewResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideosByMovieId(@Path("movie_id") movieId: Int): MovieVideoResponse
}