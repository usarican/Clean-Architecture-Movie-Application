package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.GetMyListMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getMyListMovieUseCase: GetMyListMovieUseCase,
) : BaseViewModel() {
}