package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation



sealed class DetailUiAction {
    data object SeeAllClickAction : DetailUiAction()
    data class RecommendedMovieClickAction(val movieId: Int) : DetailUiAction()
    data object ErrorRetryAction : DetailUiAction()
    data object OnBackPressClickAction : DetailUiAction()
    data class DetailButtonClickAction(val data : MovieDetailActionButtonData) : DetailUiAction()
}