package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailAboutModel

@Composable
fun AboutPageScreen(
    modifier: Modifier = Modifier,
    movieDetailAboutModel: MovieDetailAboutModel
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) { }
}