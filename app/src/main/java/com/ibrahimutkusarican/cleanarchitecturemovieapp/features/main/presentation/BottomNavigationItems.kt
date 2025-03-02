package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListPage

data class BottomNavigationItems(
    val itemName: String,
    @StringRes val itemLabel: Int,
    val navigationRoute: NavigationRoutes,
    @DrawableRes val iconResourceId: Int
) {
    companion object {
        val items = listOf(
            BottomNavigationItems(
                itemName = "movies",
                itemLabel = R.string.movies,
                navigationRoute = NavigationRoutes.BottomNavRoutes.Home,
                iconResourceId = R.drawable.ic_movie
            ),
            BottomNavigationItems(
                itemName = "search",
                itemLabel = R.string.explore,
                navigationRoute = NavigationRoutes.BottomNavRoutes.Explore,
                iconResourceId = R.drawable.ic_search_global
            ),
            BottomNavigationItems(
                itemName = "myList",
                itemLabel = R.string.my_list,
                navigationRoute = NavigationRoutes.BottomNavRoutes.MyList(MyListPage.FAVORITE.index),
                iconResourceId = R.drawable.ic_my_list
            ),
            BottomNavigationItems(
                itemName = "settings",
                itemLabel = R.string.settings,
                navigationRoute = NavigationRoutes.BottomNavRoutes.Settings,
                iconResourceId = R.drawable.ic_settings
            ),
        )
    }
}
