package com.iusarican.data.remote

import com.iusarican.data.remote.GenreService
import javax.inject.Inject

class GenreRemoteDataSource @Inject constructor(
    private val genreService: GenreService
) {
    suspend fun getMovieGenreList() = genreService.getMovieGenres()
}