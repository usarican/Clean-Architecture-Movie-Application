package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.LoadingScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBarModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.SnackBarType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    val movies by homeViewModel.movies.collectAsStateWithLifecycle()
    val refreshUiState by homeViewModel.refreshUiState.collectAsStateWithLifecycle()
    when (homeUiState) {
        is UiState.Error -> ErrorScreen(exception = (homeUiState as UiState.Error).exception,
            tryAgainOnClickAction = { homeViewModel.handleUiAction(HomeUiAction.ErrorRetryAction) })

        UiState.Loading -> LoadingScreen()
        is UiState.Success -> HomeSuccessScreen(
            movies = movies, refreshUiState = refreshUiState, action = homeViewModel::handleUiAction
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSuccessScreen(
    movies: Map<MovieType, List<BasicMovieModel>>,
    refreshUiState: UiState<Map<MovieType, List<BasicMovieModel>>>?,
    action: (HomeUiAction) -> Unit
) {
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    val state = rememberPullToRefreshState()
    val context = LocalContext.current


    PullToRefreshBox(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars), state = state, isRefreshing = refreshUiState == UiState.Loading, onRefresh = {
        action.invoke(HomeUiAction.PullToRefreshAction)
    }) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = dimensionResource(R.dimen.medium_padding))
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
            ) {
                HomeBannerMoviesList(
                    modifier = Modifier
                        .height(screenHeightDp * 0.4F)
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.small_padding)),
                    basicMovieModels = movies[MovieType.NOW_PLAYING] ?: emptyList(),
                    movieClickAction = { index ->
                        action.invoke(HomeUiAction.BannerMovieClickAction(index))
                    }
                )
                MovieCategoryList(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.twenty_dp)),
                    movies = movies,
                    seeAllClickAction = { seeAllType ->
                        action.invoke(HomeUiAction.SeeAllClickAction(seeAllType))
                    },
                    movieClickAction = { movieId ->
                        action.invoke(HomeUiAction.MovieClickAction(movieId))
                    })
            }
            if (refreshUiState is UiState.Error) {
                MySnackBar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    snackBarModel = MySnackBarModel(
                        title = stringResource(R.string.error_snackbar_title),
                        message = (refreshUiState).exception.message,
                        type = SnackBarType.ERROR
                    ),
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




