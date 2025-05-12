package com.iusarican.domain.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.iusarican.domain.model.GenreModel
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getMovieGenreList() : Flow<ApiState<List<GenreModel>>>
    fun deleteAllGenreList() : Flow<ApiState<Boolean>>
}