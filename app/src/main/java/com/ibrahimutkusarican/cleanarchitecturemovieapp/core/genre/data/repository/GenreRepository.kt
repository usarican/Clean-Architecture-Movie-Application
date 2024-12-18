package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getMovieGenreList() : Flow<ApiState<List<GenreEntity>>>
}