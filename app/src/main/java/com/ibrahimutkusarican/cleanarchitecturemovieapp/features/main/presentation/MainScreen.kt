package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation.MovieDetailScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation.MovieDetailViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.presentation.ExploreScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation.BannerMoviesScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation.HomeScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation.HomeViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListPage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation.MyListScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation.SearchScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation.SearchViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation.SeeAllScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation.SeeAllViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.presentation.SettingsScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    var myListSelectedPageIndex by remember { mutableIntStateOf(MyListPage.FAVORITE.index) }
    LaunchedEffect(true) {
        viewModel.navigationChannel.consumeAsFlow().collectLatest { route ->
            when (route) {
                is NavigationRoutes.BottomNavRoutes -> {
                    if (route is NavigationRoutes.BottomNavRoutes.MyList) myListSelectedPageIndex =
                        route.pageIndex
                    selectedItemIndex = route.index
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }

                is NavigationRoutes.ClickActionRoutes -> {
                    navController.navigate(route)
                }

                null -> navController.popBackStack()
            }
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        BottomNavigationBar(
            modifier = Modifier,
            navController = navController,
            selectedItemIndex = selectedItemIndex,
            onItemSelected = { index ->
                selectedItemIndex = index
            }
        )
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.BottomNavRoutes.Home,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable<NavigationRoutes.BottomNavRoutes.Home> {
                val homeViewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(homeViewModel)
            }
            composable<NavigationRoutes.BottomNavRoutes.Explore> {
                ExploreScreen()
            }
            composable<NavigationRoutes.BottomNavRoutes.MyList> {
                MyListScreen(myListSelectedPageIndex)
            }
            composable<NavigationRoutes.BottomNavRoutes.Settings> {
                SettingsScreen()
            }
            composable<NavigationRoutes.ClickActionRoutes.SeeAll> { backStackEntry ->
                val seeAllViewModel = hiltViewModel<SeeAllViewModel>()
                val args = backStackEntry.toRoute<NavigationRoutes.ClickActionRoutes.SeeAll>()
                val seeAllType = args.screenType.seeAllScreenTypeToSeeAllType(args.movieId)
                seeAllViewModel.setSeeAllType(seeAllType)
                SeeAllScreen(viewModel = seeAllViewModel)
            }
            composable<NavigationRoutes.ClickActionRoutes.Search> { backStackEntry ->
                val searchViewModel = hiltViewModel<SearchViewModel>()
                val recommendedMovieId =
                    backStackEntry.toRoute<NavigationRoutes.ClickActionRoutes.Search>().recommendedMovieId
                SearchScreen(viewModel = searchViewModel, recommendedMovieId = recommendedMovieId)
            }
            composable<NavigationRoutes.ClickActionRoutes.MovieDetail> { backStackEntry ->
                val detailViewModel = hiltViewModel<MovieDetailViewModel>()
                val movieId =
                    backStackEntry.toRoute<NavigationRoutes.ClickActionRoutes.MovieDetail>().movieId
                LaunchedEffect(movieId) {
                    detailViewModel.getMovieDetail(movieId)
                }
                MovieDetailScreen(viewModel = detailViewModel)
            }
            composable<NavigationRoutes.ClickActionRoutes.BannerMovies> { backStackEntry ->
                val clickedItemIndex =
                    backStackEntry.toRoute<NavigationRoutes.ClickActionRoutes.BannerMovies>().clickedItemIndex
                BannerMoviesScreen(clickItemIndex = clickedItemIndex)
            }
        }
    }
}