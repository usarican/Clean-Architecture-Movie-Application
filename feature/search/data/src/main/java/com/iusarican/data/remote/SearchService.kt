package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.remote

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") searchText: String, @Query("page") page: Int
    ): MovieResponse

    @GET("discover/movie")
    suspend fun filterAndSortMovies(
        @Query("page") page: Int,
        @Query("primary_release_year") releaseYear : Int?,
        @Query("sort_by") sortBy : String,
        @Query("with_genres") genres : String?,
        @Query("region") region : String?
    ) : MovieResponse
}