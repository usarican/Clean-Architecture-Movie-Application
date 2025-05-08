package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.network

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailCreditResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailReviewResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailVideoResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailByMovieId(
        @Path("movie_id") movieId: Int
    ): MovieDetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCreditsByMovieId(
        @Path("movie_id") movieId: Int
    ): MovieDetailCreditResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendationsByMovieId(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviewsByMovieId(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): MovieDetailReviewResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideosByMovieId(
        @Path("movie_id") movieId: Int
    ): MovieDetailVideoResponse
}