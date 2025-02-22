package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchFilterModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchScreenModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.GetSearchFilterModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.GetSearchScreenModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.SearchMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.SEARCH_DEBOUNCE_TIME
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getSearchScreenModelUseCase: GetSearchScreenModelUseCase,
    private val getSearchFilterModelUseCase: GetSearchFilterModelUseCase
) : BaseViewModel() {

    private val _searchScreenModel = MutableStateFlow(SearchScreenModel())
    val searchScreenModel: StateFlow<SearchScreenModel> = _searchScreenModel

    private val _searchScreenUiState = MutableStateFlow<UiState<SearchScreenModel>>(UiState.Loading)
    val searchScreenUiState: StateFlow<UiState<SearchScreenModel>> = _searchScreenUiState

    private var recommendedMovieId: Int? = null

    private lateinit var defaultSearchFilterModel: SearchFilterModel
    private val _searchFilterState =
        MutableStateFlow<Pair<Boolean, SearchFilterModel?>>(false to null)
    val searchFilterState: StateFlow<Pair<Boolean, SearchFilterModel?>> = _searchFilterState

    fun getSearchScreenModel(recommendedMovieId: Int?) {
        this.recommendedMovieId = recommendedMovieId
        getSearchScreenModelUseCase.getScreenModelUseCase(movieId = recommendedMovieId)
            .doOnSuccess { model -> _searchScreenModel.value = model }
            .onEach { state -> _searchScreenUiState.value = state }
            .launchIn(viewModelScope)
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchedMovies =
        _searchScreenModel.map { it.searchText }.filter { it.isNotEmpty() }.debounce(
            SEARCH_DEBOUNCE_TIME
        ).flatMapLatest { searchQuery ->
            searchMoviesUseCase.searchSeeAllMovies(searchText = searchQuery)
        }.cachedIn(viewModelScope)

    fun handleSearchScreenAction(searchUiAction: SearchUiAction) {
        when (searchUiAction) {
            is SearchUiAction.MovieClick -> sendEvent(MyEvent.MovieClickEvent(searchUiAction.movieId))
            SearchUiAction.OnBackPress -> sendEvent(MyEvent.OnBackPressed)
            is SearchUiAction.SearchAction -> {
                setSearchText(searchUiAction.searchText)
            }

            SearchUiAction.LastSearchAllClearAction -> TODO()
            is SearchUiAction.LastSearchItemClickAction -> setSearchText(searchUiAction.lastSearchItemText)
            is SearchUiAction.LastSearchItemDeleteClickAction -> TODO()
            is SearchUiAction.RecommendedMovieSeeAllClickAction -> TODO()
            is SearchUiAction.TopSearchItemClickAction -> setSearchText(searchUiAction.topSearchItemText)
            SearchUiAction.ErrorTryAgainAction -> TODO()
            is SearchUiAction.FilterAndSortActions.FilterAndSortApplyAction -> {
                filterApply(searchUiAction.newSearchFilterModel)
            }

            is SearchUiAction.FilterAndSortActions.FilterAndSortButtonClickAction -> getSearchFilterModel(
                searchUiAction.searchFilterModel
            )

            SearchUiAction.FilterAndSortActions.FilterAndSortCloseAction -> filterScreenCloseAction()
            SearchUiAction.FilterAndSortActions.FilterAndSortResetAction -> filterScreenResetAction()
            is SearchUiAction.FilterAndSortActions.UpdateFilterModel -> updateSearchFilterModel(
                searchUiAction.newFilterModel
            )
        }
    }

    private fun updateSearchFilterModel(searchFilterModel: SearchFilterModel) {
        viewModelScope.launch {
            _searchFilterState.update { true to searchFilterModel }
        }
    }

    private fun getSearchFilterModel(searchFilterModel: SearchFilterModel?) {
        if (searchFilterModel == null) {
            getSearchFilterModelUseCase.getSearchFilterModel()
                .doOnSuccess { model ->
                    _searchFilterState.value = true to model
                    defaultSearchFilterModel = model
                }
                .launchIn(viewModelScope)

        } else {
            _searchFilterState.update { true to searchFilterModel }
        }
    }

    private fun filterScreenCloseAction() {
        viewModelScope.launch {
            _searchFilterState.update { false to searchFilterState.value.second }
        }
    }

    private fun filterScreenResetAction() {
        viewModelScope.launch {
            _searchFilterState.update { true to defaultSearchFilterModel }
        }
    }

    private fun filterApply(searchFilterModel: SearchFilterModel?) {
        viewModelScope.launch {
            val selectedRefreshModel = searchFilterModel?.copy(
                genres = searchFilterModel.genres.filter { it.isSelected },
                regions = searchFilterModel.regions.filter { it.isSelected },
                timePeriods = searchFilterModel.timePeriods.filter { it.isSelected },
                sorts = searchFilterModel.sorts.filter { it.isSelected }
            )
            Log.d("SearchViewModel", "filterApply: $selectedRefreshModel")
            _searchFilterState.update { false to searchFilterModel }
        }
    }

    private fun setSearchText(searchText: String) {
        _searchScreenModel.update { it.copy(searchText = searchText) }
    }
}