package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllScreenType
import kotlinx.serialization.Serializable


sealed class NavigationRoutes(
) {
    @Serializable
    data object Settings : NavigationRoutes()

    @Serializable
    data object Home : NavigationRoutes()

    @Serializable
    data object MyList : NavigationRoutes()

    @Serializable
    data object Explore : NavigationRoutes()

    @Serializable
    data class SeeAll(
        val screenType: SeeAllScreenType,
        val movieId: Int = 0
    ) : NavigationRoutes()

    @Serializable
    data class Search(val recommendedMovieId: Int?) : NavigationRoutes()

    @Serializable
    data class MovieDetail(val movieId: Int) : NavigationRoutes()

    @Serializable
    data class BannerMovies(val clickedItemIndex: Int) : NavigationRoutes()

}