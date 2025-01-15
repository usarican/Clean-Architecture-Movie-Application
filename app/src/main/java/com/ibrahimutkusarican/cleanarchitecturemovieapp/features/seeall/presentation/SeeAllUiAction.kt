package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation

sealed class SeeAllUiAction {
    data object OnBackPress : SeeAllUiAction()
    data class MovieClick(val movieId: Int) : SeeAllUiAction()
    data class SearchAction(val searchText : String) : SeeAllUiAction()
}