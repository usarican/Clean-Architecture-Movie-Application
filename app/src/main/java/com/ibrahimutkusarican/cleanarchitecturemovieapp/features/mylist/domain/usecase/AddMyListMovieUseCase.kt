package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListMovieModel
import kotlinx.coroutines.flow.Flow

interface AddMyListMovieUseCase {
    fun addMyListMovie(movieModel: MyListMovieModel) : Flow<UiState<MyListMovieModel>>
}