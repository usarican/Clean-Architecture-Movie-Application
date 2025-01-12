package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase.GetCachedSeeAllMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase.GetPagingSeeAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val getCachedSeeAllMoviesUseCase: GetCachedSeeAllMoviesUseCase,
    private val getPagingSeeAllMoviesUseCase: GetPagingSeeAllMoviesUseCase
) : BaseViewModel() {

    private val movieType = MutableStateFlow<MovieType?>(null)

    fun setMovieType(movieType: MovieType){
        this.movieType.value = movieType
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagingMovies = movieType.filterNotNull().flatMapLatest { movieType ->
        getPagingSeeAllMoviesUseCase.getPagingSeeAllMoviesByType(movieType)
    }.cachedIn(viewModelScope)

    fun handleUiActions(uiAction: SeeAllUiAction){
        when(uiAction){
            is SeeAllUiAction.MovieClick -> TODO()
            SeeAllUiAction.OnBackPress -> TODO()
            is SeeAllUiAction.SearchAction -> TODO()
        }
    }
}