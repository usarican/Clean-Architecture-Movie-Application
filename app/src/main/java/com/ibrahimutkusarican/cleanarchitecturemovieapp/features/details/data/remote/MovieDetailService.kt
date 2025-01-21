package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetailByMovieId(@Path("movie_id") movieId: Int): MovieDetailResponse
}