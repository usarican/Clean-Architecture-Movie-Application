package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.SeeAllType

sealed class ExploreUiAction {
    data class SeeAllClickAction(val seeAllType: SeeAllType) : ExploreUiAction()
    data class MovieClickAction(val movieId: Int,val sharedAnimationKey : String) : ExploreUiAction()
    data class BannerMovieClickAction(val movieId: Int) : ExploreUiAction()
    data object ErrorRetryAction : ExploreUiAction()
    data class SearchBarClickAction(val recommendedMovieId : Int?) : ExploreUiAction()
    data class ForYouMovieClickAction(val movieId: Int) : ExploreUiAction()
}