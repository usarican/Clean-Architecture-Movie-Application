package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllType


sealed class DetailUiAction {
    data class SeeAllClickAction(val seeAllType: SeeAllType) : DetailUiAction()
    data class RecommendedMovieClickAction(val movieId: Int) : DetailUiAction()
    data object ErrorRetryAction : DetailUiAction()
    data object OnBackPressClickAction : DetailUiAction()
    data class DetailButtonClickAction(val data : MovieDetailActionButtonData) : DetailUiAction()
    data class GoToMyListPage(val pageIndex : Int) : DetailUiAction()
    data object PlayerViewOnBackPressed : DetailUiAction()
    data object OpenPlayerView : DetailUiAction()
}