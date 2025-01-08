package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.LoadingScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    val movies by homeViewModel.movies.collectAsStateWithLifecycle()
    Log.d("Utku", "UI State $homeUiState")
    when (homeUiState) {
        is UiState.Error -> ErrorScreen(exception = (homeUiState as UiState.Error).exception)
        UiState.Loading -> LoadingScreen()
        is UiState.Success -> HomeSuccessScreen(movies = movies, action = homeViewModel::handleUiAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSuccessScreen(
    movies: Map<MovieType, List<HomeMovieModel>>,
    action: (HomeUiAction) -> Unit
) {
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    var isRefreshing by remember { mutableStateOf(false) }
    val state = rememberPullToRefreshState()
    PullToRefreshBox(state = state, isRefreshing = isRefreshing, onRefresh = {
        isRefreshing = true
        action.invoke(HomeUiAction.PullToRefreshAction)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
        ) {
            BannerMoviesList(
                modifier = Modifier
                    .height(screenHeightDp * 0.4F)
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.small_padding)),
                homeMovieModels = movies[MovieType.NOW_PLAYING] ?: emptyList()
            )
            MovieCategoryList(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.twenty_dp)),
                movies = movies,
                seeAllClickAction = { movieType ->
                    action.invoke(HomeUiAction.SeeAllClickAction(movieType))
                }
            )
        }
    }

}



