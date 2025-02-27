package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchFilterModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchItemModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchScreenModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.AddLastSearchUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.DeleteLastSearchUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.FilterMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.GetSearchFilterModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.GetSearchScreenModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase.SearchMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.SEARCH_DEBOUNCE_TIME
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.SearchFilterHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
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
    private val getSearchFilterModelUseCase: GetSearchFilterModelUseCase,
    private val filterMoviesUseCase: FilterMoviesUseCase,
    private val searchFilterHelper: SearchFilterHelper,
    private val deleteLastSearchUseCase: DeleteLastSearchUseCase,
    private val addLastSearchUseCase: AddLastSearchUseCase
) : BaseViewModel() {

    private val _searchScreenModel = MutableStateFlow(SearchScreenModel())
    val searchScreenModel: StateFlow<SearchScreenModel> = _searchScreenModel

    private val _searchScreenUiState = MutableStateFlow<UiState<SearchScreenModel>>(UiState.Loading)
    val searchScreenUiState: StateFlow<UiState<SearchScreenModel>> = _searchScreenUiState

    private var recommendedMovieId: Int? = null

    private var defaultSearchFilterModel: SearchFilterModel? = null

    private val _searchFilterState =
        MutableStateFlow<Pair<Boolean, SearchFilterModel?>>(false to null)
    val searchFilterState: StateFlow<Pair<Boolean, SearchFilterModel?>> = _searchFilterState

    private val _searchFilterModel = MutableStateFlow<SearchFilterModel?>(null)

    fun getSearchScreenModel(recommendedMovieId: Int?) {
        this.recommendedMovieId = recommendedMovieId
        getSearchScreenModelUseCase.getScreenModelUseCase(movieId = recommendedMovieId)
            .doOnSuccess { model -> _searchScreenModel.value = model }
            .onEach { state -> _searchScreenUiState.value = state }.launchIn(viewModelScope)
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private val searchedMovies =
        _searchScreenModel.map { it.searchText }.filter { it.isNotEmpty() }.debounce(
            SEARCH_DEBOUNCE_TIME
        ).flatMapLatest { searchQuery ->
            searchMoviesUseCase.searchSeeAllMovies(searchText = searchQuery)
        }


    @OptIn(ExperimentalCoroutinesApi::class)
    private val filteredMovies: Flow<PagingData<SeeAllMovieModel>> =
        _searchFilterModel.filterNotNull().flatMapLatest { model ->
            filterMoviesUseCase.filterMovies(
                searchFilterModel = model
            )
        }


    @OptIn(ExperimentalCoroutinesApi::class)
    val finalSearchedOrFilteredMovies: Flow<PagingData<SeeAllMovieModel>> = combine(
        _searchScreenModel, _searchFilterModel
    ) { searchScreenModel, filterModel ->
        when {
            searchScreenModel.searchText.isEmpty() -> if (defaultSearchFilterModel != null && filterModel != defaultSearchFilterModel) {
                filteredMovies
            } else {
                flowOf(PagingData.empty())
            }

            searchScreenModel.searchText.isNotEmpty() -> {
                removeFilter()
                searchedMovies
            }

            else -> flowOf(PagingData.empty())
        }
    }.flatMapLatest { it }.cachedIn(viewModelScope)

    private val _filterList = MutableStateFlow<List<String>>(emptyList())
    val filterList: StateFlow<List<String>> = _filterList


    fun handleSearchScreenAction(searchUiAction: SearchUiAction) {
        when (searchUiAction) {
            is SearchUiAction.MovieClick -> sendEvent(MyEvent.MovieClickEvent(searchUiAction.movieId))
            SearchUiAction.OnBackPress -> sendEvent(MyEvent.OnBackPressed)
            is SearchUiAction.SearchAction -> setSearchText(searchUiAction.searchText)
            SearchUiAction.LastSearchAllClearAction -> deleteAllLastSearch()
            is SearchUiAction.LastSearchItemClickAction -> setSearchText(searchUiAction.lastSearchItemText)
            is SearchUiAction.LastSearchItemDeleteClickAction -> deleteLastSearch(searchUiAction.lastSearchItem)
            is SearchUiAction.RecommendedMovieSeeAllClickAction -> TODO()
            is SearchUiAction.TopSearchItemClickAction -> setSearchText(searchUiAction.topSearchItemText)
            SearchUiAction.ErrorTryAgainAction -> getSearchScreenModel(recommendedMovieId)
            is SearchUiAction.FilterAndSortActions.FilterAndSortApplyAction -> filterApply(
                searchUiAction.newSearchFilterModel
            )

            is SearchUiAction.FilterAndSortActions.FilterAndSortButtonClickAction -> getSearchFilterModel(
                searchUiAction.searchFilterModel
            )

            SearchUiAction.FilterAndSortActions.FilterAndSortCloseAction -> filterScreenCloseAction()
            SearchUiAction.FilterAndSortActions.FilterAndSortResetAction -> filterScreenResetAction()
            is SearchUiAction.FilterAndSortActions.UpdateFilterModel -> updateSearchFilterModel(
                searchUiAction.newFilterModel
            )

            is SearchUiAction.AddLastSearchedText -> addLastSearch(searchUiAction.searchText)
        }
    }

    private fun deleteLastSearch(searchItemModel: SearchItemModel) {
        deleteLastSearchUseCase.deleteLastSearch(searchItemModel).doOnSuccess { newList ->
            _searchScreenModel.update { it.copy(lastSearchQueries = newList) }
        }.launchIn(viewModelScope)
    }

    private fun deleteAllLastSearch() {
        deleteLastSearchUseCase.deleteAllLastSearch().doOnSuccess { newList ->
            _searchScreenModel.update { it.copy(lastSearchQueries = newList) }
        }.launchIn(viewModelScope)
    }

    private fun updateSearchFilterModel(searchFilterModel: SearchFilterModel) {
        viewModelScope.launch {
            _searchFilterState.update { true to searchFilterModel }
        }
    }

    private fun getSearchFilterModel(searchFilterModel: SearchFilterModel?) {
        if (searchFilterModel == null) {
            getSearchFilterModelUseCase.getSearchFilterModel().doOnSuccess { model ->
                _searchFilterState.value = true to model
                defaultSearchFilterModel = model
            }.launchIn(viewModelScope)

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

    private fun removeFilter() {
        viewModelScope.launch {
            _searchFilterState.update { false to defaultSearchFilterModel }
            _filterList.value = emptyList()
            _searchFilterModel.value = null
        }
    }


    private fun filterApply(searchFilterModel: SearchFilterModel?) {
        viewModelScope.launch {
            val selectedFilterModel =
                searchFilterModel?.copy(genres = searchFilterModel.genres.filter { it.isSelected },
                    regions = searchFilterModel.regions.filter { it.isSelected },
                    timePeriods = searchFilterModel.timePeriods.filter { it.isSelected },
                    sorts = searchFilterModel.sorts.filter { it.isSelected })
            _searchFilterState.update { false to searchFilterModel }
            if (searchFilterState.value.second != defaultSearchFilterModel) {
                setSearchText(Constants.EMPTY_STRING)
                _filterList.update { searchFilterHelper.getFilterItemList(selectedFilterModel) }
                _searchFilterModel.value = selectedFilterModel
            }
        }
    }

    private fun addLastSearch(searchText: String) {
        addLastSearchUseCase.addLastSearch(searchText).doOnSuccess { newList ->
            _searchScreenModel.update { it.copy(lastSearchQueries = newList) }
        }.launchIn(viewModelScope)
    }

    private fun setSearchText(searchText: String) {
        _searchScreenModel.update { it.copy(searchText = searchText) }
    }
}