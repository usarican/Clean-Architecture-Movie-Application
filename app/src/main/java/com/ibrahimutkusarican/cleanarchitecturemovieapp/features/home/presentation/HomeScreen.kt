package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.LoadingScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
){
    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    val movies by homeViewModel.movies.collectAsStateWithLifecycle()
    when(homeUiState){
        is UiState.Error -> ErrorScreen(exception = (homeUiState as UiState.Error).exception)
        UiState.Loading -> LoadingScreen()
        is UiState.Success -> HomeSuccessScreen(movies)
    }
}

@Composable
fun HomeSuccessScreen(movies: Map<MovieType, List<HomeMovieModel>>) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BannerMoviesScreen(modifier = Modifier.height(300.dp).fillMaxWidth().weight(.4F,fill = false), homeMovieModels = movies[MovieType.NOW_PLAYING] ?: emptyList())
        PopularMoviesScreen(movies[MovieType.POPULAR] ?: emptyList())
        TopRatedMoviesScreen(movies[MovieType.TOP_RATED] ?: emptyList())
        UpcomingMoviesScreen(movies[MovieType.UPCOMING] ?: emptyList())
    }
}



