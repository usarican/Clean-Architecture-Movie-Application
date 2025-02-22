package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchFilterModel

sealed class SearchUiAction {
    data object OnBackPress : SearchUiAction()
    data class MovieClick(val movieId: Int) : SearchUiAction()
    data class SearchAction(val searchText : String) : SearchUiAction()
    data class TopSearchItemClickAction(val topSearchItemText : String) : SearchUiAction()
    data object LastSearchAllClearAction : SearchUiAction()
    data class LastSearchItemClickAction(val lastSearchItemText : String) : SearchUiAction()
    data class LastSearchItemDeleteClickAction(val lastSearchItemText: String) : SearchUiAction()
    data object RecommendedMovieSeeAllClickAction : SearchUiAction()
    data object ErrorTryAgainAction : SearchUiAction()

    sealed class FilterAndSortActions : SearchUiAction() {
        data class FilterAndSortButtonClickAction(val searchFilterModel: SearchFilterModel?) : FilterAndSortActions()
        data class FilterAndSortApplyAction(val newSearchFilterModel : SearchFilterModel) : FilterAndSortActions()
        data object FilterAndSortResetAction : FilterAndSortActions()
        data object FilterAndSortCloseAction : FilterAndSortActions()
        data class UpdateFilterModel(val newFilterModel : SearchFilterModel) : FilterAndSortActions()
    }
}