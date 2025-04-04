package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import kotlinx.coroutines.flow.Flow

interface GetHomeMoviesUseCase {
    fun getHomeMoviesUseCase(): Flow<UiState<Map<MovieType, List<BasicMovieModel>>>>
}