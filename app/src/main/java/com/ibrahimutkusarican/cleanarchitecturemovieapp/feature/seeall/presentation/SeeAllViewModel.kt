package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.usecase.SearchMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.SeeAllType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.usecase.GetSeeAllUseCase
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
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val getSeeAllUseCase: GetSeeAllUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : BaseViewModel() {

    private val _movieType = MutableStateFlow<MovieType?>(null)
    val movieType: StateFlow<MovieType?> = _movieType

    private val _seeAllType = MutableStateFlow<SeeAllType?>(null)
    val seeAllType: StateFlow<SeeAllType?> = _seeAllType

    private val _searchText = MutableStateFlow(EMPTY_STRING)
    val searchText: StateFlow<String> = _searchText


    fun setSeeAllType(seeAllType: SeeAllType?){
        _seeAllType.update { seeAllType }
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val pagingMovies = searchText.debounce(
        SEARCH_DEBOUNCE_TIME
    ).flatMapLatest { searchQuery ->
        if (searchQuery.isNotEmpty()) {
            searchMoviesUseCase.searchSeeAllMovies(searchText = searchQuery)
        } else {
            seeAllType.filterNotNull().flatMapLatest { type ->
                when (type) {
                    is SeeAllType.RecommendationMovies -> getSeeAllUseCase.getSeeAllRecommendedMovies(
                        type.movieId
                    )

                    is SeeAllType.SeeAllMovieType -> getSeeAllUseCase.getSeeAllMoviesByType(type.movieType)
                    is SeeAllType.MovieReviews -> flowOf(PagingData.empty())
                }
            }
        }
    }.cachedIn(viewModelScope)

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagingReviews = seeAllType.filterNotNull().flatMapLatest { type ->
        if (type is SeeAllType.MovieReviews) {
            getSeeAllUseCase.getMovieReviewsSeeAll(type.movieId)
        } else flowOf(PagingData.empty())
    }

    fun handleUiActions(uiAction: SeeAllUiAction) {
        when (uiAction) {
            is SeeAllUiAction.MovieClick -> sendEvent(MyEvent.MovieClickEvent(uiAction.movieId))
            SeeAllUiAction.OnBackPress -> sendEvent(MyEvent.OnBackPressed)
            is SeeAllUiAction.SearchAction -> {
                _searchText.value = uiAction.searchText
            }
        }
    }
}