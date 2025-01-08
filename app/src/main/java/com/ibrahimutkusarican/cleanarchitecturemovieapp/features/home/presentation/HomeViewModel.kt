package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.GetHomeMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeMoviesUseCase: GetHomeMoviesUseCase
) : BaseViewModel() {

    private val _movies = MutableStateFlow<Map<MovieType, List<HomeMovieModel>>>(mapOf())
    val movies: StateFlow<Map<MovieType, List<HomeMovieModel>>> = _movies

    private val _homeUiState = MutableStateFlow<UiState<Map<MovieType, List<HomeMovieModel>>>>(UiState.Loading)
    val homeUiState: StateFlow<UiState<Map<MovieType, List<HomeMovieModel>>>> = _homeUiState

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)

    init {
        refreshTrigger
            .onStart { emit(Unit) } // Emit initial value to trigger the flow
            .flatMapLatest {
                getHomeMoviesUseCase.getHomeMoviesUseCase()
            }
            .doOnSuccess { movies ->
                _movies.value = movies
            }
            .onEach { uiState ->
            _homeUiState.value = uiState
        }.launchIn(viewModelScope)
    }

    private fun refreshMovies() {
        viewModelScope.launch {
            refreshTrigger.emit(Unit)
        }
    }

    fun handleUiAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.PullToRefreshAction -> refreshMovies()
            else -> {}
        }
    }
}