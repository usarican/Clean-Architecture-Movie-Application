package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.presentation.ExploreScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation.HomeScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation.MyListScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.presentation.SettingsScreen

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(
            modifier = Modifier,
            navController = navController
        ) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<NavigationRoutes.Home> {
                HomeScreen()
            }
            composable<NavigationRoutes.Explore> {
                ExploreScreen()
            }
            composable<NavigationRoutes.MyList> {
                MyListScreen()
            }
            composable<NavigationRoutes.Settings> {
                SettingsScreen()
            }
        }
    }
}