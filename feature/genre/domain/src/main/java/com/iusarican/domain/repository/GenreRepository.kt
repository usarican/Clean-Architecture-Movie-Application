package com.iusarican.domain.repository


import com.iusarican.domain.model.GenreModel

interface GenreRepository {
    suspend fun getMovieGenreList() : List<GenreModel>
    suspend fun deleteAllGenreList() : Boolean
}