package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailRecommendedMovieModel

@Composable
fun RecommendedPageScreen(
    modifier: Modifier = Modifier,
    movieDetailRecommendedMovieModel :MovieDetailRecommendedMovieModel
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) { }
}