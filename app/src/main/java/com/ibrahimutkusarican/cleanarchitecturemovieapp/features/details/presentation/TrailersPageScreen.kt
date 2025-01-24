package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailTrailerModel

@Composable
fun TrailersPageScreen(
    modifier: Modifier = Modifier,
    movieDetailTrailerModel: MovieDetailTrailerModel
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) { }
}