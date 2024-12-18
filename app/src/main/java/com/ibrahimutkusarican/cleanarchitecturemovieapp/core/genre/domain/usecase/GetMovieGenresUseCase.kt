package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import kotlinx.coroutines.flow.Flow

interface GetMovieGenresUseCase {
    fun getMovieGenresUseCase() : Flow<ApiState<List<GenreModel>>>
}