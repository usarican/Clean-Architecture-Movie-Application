package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import kotlinx.coroutines.flow.Flow

interface AddMyListMovieUseCase {
    fun addMyListMovieFromDetail(movieDetailModel: MovieDetailModel) : Flow<UiState<Unit>>
    fun addMyListMovieFromHome(basicMovieModel: BasicMovieModel) : Flow<UiState<Unit>>
}