package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.State
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getMovieGenreList() : Flow<State<List<GenreEntity>>>
}