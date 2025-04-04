package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.model.GenreModel
import kotlinx.coroutines.flow.Flow

interface GetMovieGenresUseCase {
    fun getMovieGenresUseCase() : Flow<ApiState<List<GenreModel>>>
}