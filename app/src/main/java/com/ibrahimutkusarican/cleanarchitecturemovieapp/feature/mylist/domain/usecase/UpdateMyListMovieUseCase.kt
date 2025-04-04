package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.domain.model.MyListUpdatePage
import kotlinx.coroutines.flow.Flow

interface UpdateMyListMovieUseCase {
    fun updateMyListMovieFromHome(basicMovieModel: BasicMovieModel, myListUpdatePage: MyListUpdatePage) : Flow<UiState<BasicMovieModel>>
    fun updateMyListMovieFromDetail(movieDetailModel: MovieDetailModel, myListUpdatePage: MyListUpdatePage) : Flow<UiState<MovieDetailModel>>
}