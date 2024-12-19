package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.GetHomeMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeMoviesUseCase: GetHomeMoviesUseCase
): BaseViewModel() {


    private val _movies = MutableStateFlow<Map<MovieType, List<HomeMovieModel>>>(mapOf())
    val movies : StateFlow<Map<MovieType, List<HomeMovieModel>>> = _movies

    private val _homeUiState = MutableStateFlow<UiState<*>>(UiState.Loading)
    val homeUiState : StateFlow<UiState<*>> = _homeUiState

    init {
        val movies = getHomeMoviesUseCase.getHomeMoviesUseCase()
        movies.doOnSuccess {
            _movies.value = it
        }.onEach { uiState ->
            _homeUiState.value = uiState
        }.launchIn(viewModelScope)
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

}