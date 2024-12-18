package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.State
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import kotlinx.coroutines.flow.Flow

interface GetHomeMoviesUseCase {
    fun getHomeMoviesUseCase(genreIds: List<Int>): Flow<State<Map<MovieType,List<HomeMovieModel>>>>

}