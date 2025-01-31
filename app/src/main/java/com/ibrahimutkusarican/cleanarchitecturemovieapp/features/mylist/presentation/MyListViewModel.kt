package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListPage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.GetMyListMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getMyListMovieUseCase: GetMyListMovieUseCase,
) : BaseViewModel() {

    val favoriteMovies = getMyListMovieUseCase.getMyListMovieUseCase(page = MyListPage.FAVORITE).cachedIn(viewModelScope)
    val watchListMovies = getMyListMovieUseCase.getMyListMovieUseCase(page = MyListPage.WATCH_LIST).cachedIn(viewModelScope)
}