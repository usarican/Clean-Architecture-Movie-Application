package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(GET_NOW_PLAYING_MOVIES)
    suspend fun getNowPlayingMovies(@Query("page") page: Int): MovieResponse

    @GET(GET_POPULAR_MOVIES)
    suspend fun getPopularMovies(@Query("page") page: Int): MovieResponse

    @GET(GET_TOP_RATED_MOVIES)
    suspend fun getTopRatedMovies(@Query("page") page: Int): MovieResponse

    @GET(GET_UP_COMING_MOVIES)
    suspend fun getUpComingMovies(@Query("page") page: Int): MovieResponse

    @GET(GET_RECOMMENDED_MOVIES_BY_MOVIE_ID)
    suspend fun getRecommendedMovies(
        @Path("movie_id") movieId: Int
    ): MovieResponse


    companion object {
        private const val GET_NOW_PLAYING_MOVIES = "movie/now_playing"
        private const val GET_POPULAR_MOVIES = "movie/popular"
        private const val GET_TOP_RATED_MOVIES = "movie/top_rated"
        private const val GET_UP_COMING_MOVIES = "movie/upcoming"
        private const val GET_RECOMMENDED_MOVIES_BY_MOVIE_ID = "movie/{movie_id}/recommendations"
    }
}