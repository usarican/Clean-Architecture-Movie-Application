package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase.GetCachedSeeAllMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase.GetSeeAllMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val getCachedSeeAllMoviesUseCase: GetCachedSeeAllMoviesUseCase,
    private val getSeeAllMoviesUseCase: GetSeeAllMoviesUseCase
) : BaseViewModel() {

    private val _movieType = MutableStateFlow<MovieType?>(null)
    val movieType: StateFlow<MovieType?> = _movieType

    private val _cachedMovies = MutableStateFlow<List<SeeAllMovieModel>>(emptyList())
    val cachedMovies: StateFlow<List<SeeAllMovieModel>> = _cachedMovies

    private val _cachedMoviesUiState =
        MutableStateFlow<UiState<List<SeeAllMovieModel>>>(UiState.Loading)
    val cachedMoviesUiState: StateFlow<UiState<List<SeeAllMovieModel>>> = _cachedMoviesUiState

    init {
        fetchCachedSeeAllMoviesByMovieType()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun fetchCachedSeeAllMoviesByMovieType() {
        movieType.filterNotNull().flatMapLatest { movieType ->
            getCachedSeeAllMoviesUseCase.getCachedMoviesByType(movieType)
        }.doOnSuccess {
            _cachedMovies.value = it
        }.onEach { state ->
            _cachedMoviesUiState.value = state
        }
    }

    fun setMovieType(movieType: MovieType) {
        _movieType.value = movieType
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagingMovies = movieType.filterNotNull().flatMapLatest { movieType ->
        getSeeAllMoviesUseCase.getSeeAllMoviesByType(movieType)
    }.cachedIn(viewModelScope)

    fun handleUiActions(uiAction: SeeAllUiAction) {
        when (uiAction) {
            is SeeAllUiAction.MovieClick -> TODO()
            SeeAllUiAction.OnBackPress -> TODO()
            is SeeAllUiAction.SearchAction -> TODO()
        }
    }
}