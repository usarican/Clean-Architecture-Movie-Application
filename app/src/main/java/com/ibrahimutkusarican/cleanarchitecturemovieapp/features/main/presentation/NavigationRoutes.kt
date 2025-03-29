package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllScreenType
import kotlinx.serialization.Serializable


sealed class NavigationRoutes {

    @Serializable
    sealed class BottomNavRoutes(val index: Int) : NavigationRoutes() {
        @Serializable
        data object Home : BottomNavRoutes(index = 0)

        @Serializable
        data object Explore : BottomNavRoutes(index = 1)

        @Serializable
        data class MyList(val pageIndex : Int) : BottomNavRoutes(index = 2)

        @Serializable
        data object Settings : BottomNavRoutes(index = 3)
    }

    sealed class ClickActionRoutes : NavigationRoutes() {
        @Serializable
        data class SeeAll(
            val screenType: SeeAllScreenType,
            val movieId: Int = 0
        ) : ClickActionRoutes()

        @Serializable
        data class Search(val recommendedMovieId: Int?) : ClickActionRoutes()

        @Serializable
        data class MovieDetail(val movieId: Int,val sharedAnimationKey : String? = null) : ClickActionRoutes()

        @Serializable
        data class BannerMovies(val clickedItemIndex: Int) : ClickActionRoutes()
    }
}
