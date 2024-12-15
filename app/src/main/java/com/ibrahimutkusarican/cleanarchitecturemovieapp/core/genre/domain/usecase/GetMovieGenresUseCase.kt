package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.State
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import kotlinx.coroutines.flow.Flow

interface GetMovieGenresUseCase {
    fun getMovieGenresUseCase() : Flow<State<List<GenreModel>>>
}