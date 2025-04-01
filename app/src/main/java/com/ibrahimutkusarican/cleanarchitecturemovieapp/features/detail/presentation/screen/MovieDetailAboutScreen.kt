package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailAboutModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.mockMovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.component.MovieAboutCastComponent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.component.MovieAboutGenreComponent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.component.MovieAboutInformationComponent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.component.MovieAboutOverviewComponent

@Composable
fun MovieDetailAboutScreen(
    movieDetailAboutModel: MovieDetailAboutModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.large_padding))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding))
    ) {
        MovieAboutInformationComponent(movieDetailAboutModel = movieDetailAboutModel)
        MovieAboutGenreComponent(movieDetailAboutModel = movieDetailAboutModel)
        MovieAboutOverviewComponent(movieDetailAboutModel = movieDetailAboutModel)
        MovieAboutCastComponent(movieDetailAboutModel = movieDetailAboutModel)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieDetailAboutScreen() {
    MovieDetailAboutScreen(
        movieDetailAboutModel = mockMovieDetailModel.movieDetailAboutModel
    )
}