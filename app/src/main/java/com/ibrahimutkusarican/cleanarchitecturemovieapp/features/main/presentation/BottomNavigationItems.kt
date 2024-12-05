package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import androidx.annotation.DrawableRes
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R

data class BottomNavigationItems(
    val title: String,
    val navigationRoute : NavigationRoutes,
    @DrawableRes val iconResourceId : Int
)

val bottomNavigationItems = listOf(
    BottomNavigationItems(
        title = "Home",
        navigationRoute = NavigationRoutes.Home,
        iconResourceId = R.drawable.ic_home
    ),
    BottomNavigationItems(
        title = "Explore",
        navigationRoute = NavigationRoutes.Explore,
        iconResourceId = R.drawable.ic_explore
    ),
    BottomNavigationItems(
        title = "MyList",
        navigationRoute = NavigationRoutes.MyList,
        iconResourceId = R.drawable.ic_my_list
    ),
    BottomNavigationItems(
        title = "Settings",
        navigationRoute = NavigationRoutes.Settings,
        iconResourceId = R.drawable.ic_app_settings
    ),
)
