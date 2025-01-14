package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") searchText: String, @Query("page") page: Int
    ): MovieResponse
}