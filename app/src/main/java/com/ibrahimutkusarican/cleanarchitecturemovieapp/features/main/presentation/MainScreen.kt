package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation.MyListScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation.SearchScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation.SearchViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllScreenType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation.SeeAllScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation.SeeAllViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.presentation.SettingsScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        BottomNavigationBar(
            modifier = Modifier, navController = navController
        )
    }) { innerPadding ->
        LaunchedEffect(true) {
            viewModel.navigationChannel.consumeAsFlow().collectLatest { route ->
                if (route != null) {
                    navController.navigate(route)
                } else {
                    navController.popBackStack()
                }
            }
        }
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.Home,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable<NavigationRoutes.Home> {
                val homeViewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(homeViewModel)
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
            composable<NavigationRoutes.SeeAll> { backStackEntry ->
                val seeAllViewModel = hiltViewModel<SeeAllViewModel>()
                val args = backStackEntry.toRoute<NavigationRoutes.SeeAll>()
                val seeAllType = when (args.screenType) {
                    SeeAllScreenType.NOW_PLAYING -> SeeAllType.SeeAllMovieType.NowPlaying
                    SeeAllScreenType.POPULAR -> SeeAllType.SeeAllMovieType.NowPlaying
                    SeeAllScreenType.TOP_RATED -> SeeAllType.SeeAllMovieType.NowPlaying
                    SeeAllScreenType.UPCOMING -> SeeAllType.SeeAllMovieType.NowPlaying
                    SeeAllScreenType.RECOMMENDATION -> SeeAllType.RecommendationMovies(args.movieId)
                    SeeAllScreenType.REVIEWS -> SeeAllType.MovieReviews(args.movieId)
                }
                seeAllViewModel.setSeeAllType(seeAllType)
                SeeAllScreen(viewModel = seeAllViewModel)
            }
            composable<NavigationRoutes.Search> { backStackEntry ->
                val searchViewModel = hiltViewModel<SearchViewModel>()
                val recommendedMovieId =
                    backStackEntry.toRoute<NavigationRoutes.Search>().recommendedMovieId
                SearchScreen(viewModel = searchViewModel, recommendedMovieId = recommendedMovieId)
            }
            composable<NavigationRoutes.MovieDetail> { backStackEntry ->
                val detailViewModel = hiltViewModel<MovieDetailViewModel>()
                val movieId = backStackEntry.toRoute<NavigationRoutes.MovieDetail>().movieId
                LaunchedEffect(movieId) {
                    detailViewModel.getMovieDetail(movieId)
                }
                MovieDetailScreen(viewModel = detailViewModel)
            }
            composable<NavigationRoutes.BannerMovies> { backStackEntry ->
                val clickedItemIndex =
                    backStackEntry.toRoute<NavigationRoutes.BannerMovies>().clickedItemIndex
                BannerMoviesScreen(clickItemIndex = clickedItemIndex)
            }
        }
    }
}