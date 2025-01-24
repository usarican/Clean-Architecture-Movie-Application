package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailReviewModel

@Composable
fun ReviewsPageScreen(
    modifier: Modifier = Modifier,
    movieDetailReviewModel: MovieDetailReviewModel
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) { }
}