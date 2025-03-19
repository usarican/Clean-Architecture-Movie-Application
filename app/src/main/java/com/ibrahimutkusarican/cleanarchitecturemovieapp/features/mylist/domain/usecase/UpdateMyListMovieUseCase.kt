package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListUpdatePage
import kotlinx.coroutines.flow.Flow

interface UpdateMyListMovieUseCase {
    fun updateMyListMovieFromHome(basicMovieModel: BasicMovieModel, myListUpdatePage: MyListUpdatePage) : Flow<UiState<BasicMovieModel>>
    fun updateMyListMovieFromDetail(movieDetailModel: MovieDetailModel, myListUpdatePage: MyListUpdatePage) : Flow<UiState<MovieDetailModel>>
}