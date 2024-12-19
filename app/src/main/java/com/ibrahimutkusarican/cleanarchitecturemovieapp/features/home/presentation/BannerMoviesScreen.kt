package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel

@Composable
fun BannerMoviesScreen(
    modifier: Modifier = Modifier,
    homeMovieModels: List<HomeMovieModel>
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(homeMovieModels){ bannerMovie ->
            BannerMovieItem(bannerMovie)
        }
    }
}

@Composable
fun BannerMovieItem(bannerMovie: HomeMovieModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(bannerMovie.movieTitle)
    }
}
