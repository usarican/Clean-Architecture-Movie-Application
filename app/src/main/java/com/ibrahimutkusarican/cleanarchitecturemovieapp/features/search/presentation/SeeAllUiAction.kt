package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

sealed class SearchUiAction {
    data object OnBackPress : SearchUiAction()
    data class MovieClick(val movieId: Int) : SearchUiAction()
    data class SearchAction(val searchText : String) : SearchUiAction()
}