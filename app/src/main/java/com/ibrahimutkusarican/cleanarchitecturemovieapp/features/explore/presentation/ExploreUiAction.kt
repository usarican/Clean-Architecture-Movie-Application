package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllType

sealed class ExploreUiAction {
    data class SeeAllClickAction(val seeAllType: SeeAllType) : ExploreUiAction()
    data class MovieClickAction(val movieId: Int) : ExploreUiAction()
    data class BannerMovieClickAction(val movieId: Int) : ExploreUiAction()
    data object ErrorRetryAction : ExploreUiAction()
    data class SearchBarClickAction(val recommendedMovieId : Int?) : ExploreUiAction()
    data class ForYouMovieClickAction(val movieId: Int) : ExploreUiAction()
}