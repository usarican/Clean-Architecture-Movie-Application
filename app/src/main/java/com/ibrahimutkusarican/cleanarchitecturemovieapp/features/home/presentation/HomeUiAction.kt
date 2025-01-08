package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType

sealed class HomeUiAction {
    data class SeeAllClickAction(val movieType: MovieType) : HomeUiAction()
    data class MovieClickAction(val movieId: Int) : HomeUiAction()
    data object PullToRefreshAction : HomeUiAction()
    data object ErrorRetryAction : HomeUiAction()
}
