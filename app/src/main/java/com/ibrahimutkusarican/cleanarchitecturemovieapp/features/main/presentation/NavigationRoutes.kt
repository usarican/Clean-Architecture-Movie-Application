package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation
import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoutes(
    val route : String
) {
    @Serializable data object Settings : NavigationRoutes("Settings")
    @Serializable data object Home : NavigationRoutes("Home")
    @Serializable data object MyList : NavigationRoutes("MyList")
    @Serializable data object Explore : NavigationRoutes("Explore")
}