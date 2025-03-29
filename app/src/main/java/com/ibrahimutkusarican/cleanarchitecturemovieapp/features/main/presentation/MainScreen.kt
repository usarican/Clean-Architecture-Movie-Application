package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBarHost
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    var myListSelectedPageIndex by remember { mutableIntStateOf(MyListPage.FAVORITE.index) }
    val bottomNavigationVisibility by viewModel.bottomNavigationVisibility.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val userSettings = viewModel.userSetting.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.showSnackBar.collectLatest { snackBar ->
            snackbarHostState.showSnackbar(snackBar)
        }
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect {
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigationFlow.collectLatest { route ->
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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            MySnackBarHost(
                hostState = snackbarHostState,
                isDarkMode = userSettings.value.isDarkModeEnabled
            )
        },
        bottomBar = if (bottomNavigationVisibility) {
            {
                BottomNavigationBar(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
                    navController = navController,
                    selectedItemIndex = selectedItemIndex,
                    onItemSelected = { index ->
                        selectedItemIndex = index
                    }
                )
            }
        } else {
            {}
        }
    ) { innerPadding ->
        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = NavigationRoutes.BottomNavRoutes.Home,
                modifier = Modifier
                    .padding(bottom = innerPadding.calculateBottomPadding()),
            ) {
                composable<NavigationRoutes.BottomNavRoutes.Home> {
                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    HomeScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                        homeViewModel = homeViewModel
                    )
                }
                composable<NavigationRoutes.BottomNavRoutes.Explore> {
                    ExploreScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable
                    )
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
                    SearchScreen(
                        viewModel = searchViewModel,
                        recommendedMovieId = recommendedMovieId,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                    )
                }
                composable<NavigationRoutes.ClickActionRoutes.MovieDetail>(
                    deepLinks = listOf(
                        navDeepLink {
                            uriPattern = "movieapp://moviedetail/{movieId}"
                        }
                    )
                ) { backStackEntry ->
                    val detailViewModel = hiltViewModel<MovieDetailViewModel>()
                    val movieDetailModel =
                        backStackEntry.toRoute<NavigationRoutes.ClickActionRoutes.MovieDetail>()

                    LaunchedEffect(movieDetailModel.movieId) {
                        detailViewModel.getMovieDetail(movieDetailModel.movieId)
                    }
                    MovieDetailScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                        viewModel = detailViewModel,
                        shareAnimationKey = movieDetailModel.sharedAnimationKey
                    )
                }
                composable<NavigationRoutes.ClickActionRoutes.BannerMovies> { backStackEntry ->
                    val clickedItemIndex =
                        backStackEntry.toRoute<NavigationRoutes.ClickActionRoutes.BannerMovies>().clickedItemIndex
                    BannerMoviesScreen(clickItemIndex = clickedItemIndex)
                }
            }
        }

    }
}