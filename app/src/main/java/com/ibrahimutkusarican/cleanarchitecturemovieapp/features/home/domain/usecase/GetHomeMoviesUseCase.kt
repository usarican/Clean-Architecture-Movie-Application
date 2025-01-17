package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import kotlinx.coroutines.flow.Flow

interface GetHomeMoviesUseCase {
    fun getHomeMoviesUseCase(): Flow<UiState<Map<MovieType, List<BasicMovieModel>>>>
}