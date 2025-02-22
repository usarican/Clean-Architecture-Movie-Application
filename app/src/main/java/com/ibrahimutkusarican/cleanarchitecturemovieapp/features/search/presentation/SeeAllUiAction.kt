package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

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
}