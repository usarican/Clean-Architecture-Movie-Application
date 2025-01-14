package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.SearchSeeAllMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase.GetSeeAllMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.EMPTY_STRING
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.SEARCH_DEBOUNCE_TIME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val getSeeAllMoviesUseCase: GetSeeAllMoviesUseCase,
    private val searchSeeAllMoviesUseCase: SearchSeeAllMoviesUseCase
) : BaseViewModel() {

    private val _movieType = MutableStateFlow<MovieType?>(null)
    val movieType: StateFlow<MovieType?> = _movieType

    private val _searchText = MutableStateFlow(EMPTY_STRING)
    val searchText: StateFlow<String> = _searchText


    fun setMovieType(movieType: MovieType) {
        _movieType.value = movieType
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val pagingMovies = searchText.debounce(
        SEARCH_DEBOUNCE_TIME
    ).flatMapLatest { searchQuery ->
        if (searchQuery.isNotEmpty()) {
            searchSeeAllMoviesUseCase.searchSeeAllMovies(searchText = searchQuery)
        } else {
            movieType.filterNotNull().flatMapLatest { movieType ->
                getSeeAllMoviesUseCase.getSeeAllMoviesByType(movieType)
            }
        }
    }.cachedIn(viewModelScope)

    fun handleUiActions(uiAction: SeeAllUiAction) {
        when (uiAction) {
            is SeeAllUiAction.MovieClick -> TODO()
            SeeAllUiAction.OnBackPress -> TODO()
            is SeeAllUiAction.SearchAction -> {
                _searchText.value = uiAction.searchText
            }
        }
    }
}