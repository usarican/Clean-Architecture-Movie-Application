package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.remote

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.remote.response.GenreResponse
import retrofit2.http.GET

interface GenreService {

    @GET(GET_MOVIE_GENRE_LIST)
    suspend fun getMovieGenres(): GenreResponse

    companion object {
        private const val GET_MOVIE_GENRE_LIST = "genre/movie/list"
    }
}