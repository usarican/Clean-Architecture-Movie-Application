package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.GetHomeMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.RefreshHomeMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeMoviesUseCase: GetHomeMoviesUseCase,
    private val refreshHomeMoviesUseCase: RefreshHomeMoviesUseCase
) : BaseViewModel() {

    private val _movies = MutableStateFlow<Map<MovieType, List<BasicMovieModel>>>(mapOf())
    val movies: StateFlow<Map<MovieType, List<BasicMovieModel>>> = _movies

    private val _homeUiState =
        MutableStateFlow<UiState<Map<MovieType, List<BasicMovieModel>>>>(UiState.Loading)
    val homeUiState: StateFlow<UiState<Map<MovieType, List<BasicMovieModel>>>> = _homeUiState

    private val _refreshUiState =
        MutableStateFlow<UiState<Map<MovieType, List<BasicMovieModel>>>?>(null)
    val refreshUiState: StateFlow<UiState<Map<MovieType, List<BasicMovieModel>>>?> = _refreshUiState

    init {
        getMovies()
    }

    private fun getMovies() {
        getHomeMoviesUseCase.getHomeMoviesUseCase().doOnSuccess { movies ->
            _movies.value = movies
        }.onEach { uiState ->
            _homeUiState.value = uiState
        }.launchIn(viewModelScope)
    }

    private fun refreshMovies() {
        refreshHomeMoviesUseCase.refreshHomeMovies().doOnSuccess { movies ->
            _movies.value = movies
        }.onEach { refreshUiState ->
            _refreshUiState.emit(refreshUiState)
        }.launchIn(viewModelScope)
    }

    fun handleUiAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.PullToRefreshAction -> refreshMovies()
            is HomeUiAction.ErrorRetryAction -> getMovies()
            is HomeUiAction.SeeAllClickAction -> sendEvent(MyEvent.SeeAllClickEvent(action.movieType))
            is HomeUiAction.MovieClickAction -> sendEvent(MyEvent.MovieClickEvent(action.movieId))
            is HomeUiAction.BannerMovieClickAction -> sendEvent(MyEvent.BannerMovieClickEvent(action.clickIndex))
        }
    }
}