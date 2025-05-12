package com.iusarican.data.remote

import com.iusarican.data.remote.response.GenreResponse
import retrofit2.http.GET

interface GenreService {

    @GET(GET_MOVIE_GENRE_LIST)
    suspend fun getMovieGenres(): GenreResponse

    companion object {
        private const val GET_MOVIE_GENRE_LIST = "genre/movie/list"
    }
}