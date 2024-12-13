package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R

data class BottomNavigationItems(
    val itemName : String,
    @StringRes val itemLabel: Int,
    val navigationRoute: NavigationRoutes,
    @DrawableRes val iconResourceId: Int
) {
    companion object {
        val items = listOf(
            BottomNavigationItems(
                itemName = "movies",
                itemLabel = R.string.movies,
                navigationRoute = NavigationRoutes.Home,
                iconResourceId = R.drawable.ic_movie
            ),
            BottomNavigationItems(
                itemName = "search",
                itemLabel = R.string.search,
                navigationRoute = NavigationRoutes.Explore,
                iconResourceId = R.drawable.ic_search
            ),
            BottomNavigationItems(
                itemName = "myList",
                itemLabel = R.string.my_list,
                navigationRoute = NavigationRoutes.MyList,
                iconResourceId = R.drawable.ic_my_list
            ),
            BottomNavigationItems(
                itemName = "settings",
                itemLabel = R.string.settings,
                navigationRoute = NavigationRoutes.Settings,
                iconResourceId = R.drawable.ic_settings
            ),
        )
    }
}
