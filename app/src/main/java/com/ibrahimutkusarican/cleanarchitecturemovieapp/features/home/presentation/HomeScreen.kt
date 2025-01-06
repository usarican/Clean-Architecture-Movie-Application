package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    when (homeUiState) {
        is UiState.Error -> ErrorScreen(exception = (homeUiState as UiState.Error).exception)
        UiState.Loading -> LoadingScreen()
        is UiState.Success -> HomeSuccessScreen(movies = movies, seeAllClickAction = {})
    }
}

@Composable
fun HomeSuccessScreen(
    movies: Map<MovieType, List<HomeMovieModel>>, seeAllClickAction: (movieType: MovieType) -> Unit
) {
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
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
            seeAllClickAction = seeAllClickAction
        )
    }
}



