package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType

sealed class ExploreUiAction {
    data class SeeAllClickAction(val movieType: MovieType) : ExploreUiAction()
    data class MovieClickAction(val movieId: Int) : ExploreUiAction()
    data class BannerMovieClickAction(val movieId: Int) : ExploreUiAction()
    data object ErrorRetryAction : ExploreUiAction()
}