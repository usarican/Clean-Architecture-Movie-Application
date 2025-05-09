package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.data.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.data.local.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getMovieGenreList() : Flow<ApiState<List<GenreEntity>>>
    fun deleteAllGenreList() : Flow<ApiState<Boolean>>
}