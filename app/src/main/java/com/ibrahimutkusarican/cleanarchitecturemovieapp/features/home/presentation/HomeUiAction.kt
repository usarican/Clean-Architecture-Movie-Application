package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllType

sealed class HomeUiAction {
    data class SeeAllClickAction(val seeAllType: SeeAllType) : HomeUiAction()
    data class MovieClickAction(val movieId: Int) : HomeUiAction()
    data object PullToRefreshAction : HomeUiAction()
    data object ErrorRetryAction : HomeUiAction()
    data class BannerMovieClickAction(val clickIndex : Int) : HomeUiAction()
    data class BannerMovieSeeMoreClickAction(val movieId : Int) : HomeUiAction()
}
