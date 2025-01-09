package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.ErrorSnackBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.LoadingScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ObserveAsEvents
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    val movies by homeViewModel.movies.collectAsStateWithLifecycle()
    val refreshUiState by homeViewModel.refreshUiState.collectAsStateWithLifecycle()
    when (homeUiState) {
        is UiState.Error -> ErrorScreen(exception = (homeUiState as UiState.Error).exception)
        UiState.Loading -> LoadingScreen()
        is UiState.Success -> HomeSuccessScreen(
            movies = movies,
            refreshUiState = refreshUiState,
            action = homeViewModel::handleUiAction
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSuccessScreen(
    movies: Map<MovieType, List<HomeMovieModel>>,
    refreshUiState: UiState<Map<MovieType, List<HomeMovieModel>>>?,
    action: (HomeUiAction) -> Unit
) {
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    val state = rememberPullToRefreshState()
    val context = LocalContext.current


    PullToRefreshBox(state = state, isRefreshing = refreshUiState == UiState.Loading, onRefresh = {
        action.invoke(HomeUiAction.PullToRefreshAction)
    }) {
        Box {
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
                MovieCategoryList(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.twenty_dp)),
                    movies = movies,
                    seeAllClickAction = { movieType ->
                        action.invoke(HomeUiAction.SeeAllClickAction(movieType))
                    })
            }
            if (refreshUiState is UiState.Error) {
                ErrorSnackBar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    errorMessage = (refreshUiState).exception.message,
                    actionLabel = context.getString(R.string.retry),
                    action = {
                        action.invoke(HomeUiAction.PullToRefreshAction)
                    },
                    visible = true
                )
            }
        }
    }
}




