package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import kotlinx.coroutines.flow.Flow

interface DeleteMyListMovieUseCase {
    fun deleteMyListMovieFromDetail(movieDetailModel: MovieDetailModel) : Flow<UiState<Unit>>
    fun deleteMyListMovieFromHome(basicMovieModel: BasicMovieModel) : Flow<UiState<Unit>>
}