package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import kotlinx.coroutines.flow.Flow

interface GetCachedSeeAllMoviesUseCase {
    fun getCachedMoviesByType(movieType: MovieType) : Flow<UiState<List<SeeAllMovieModel>>>
}