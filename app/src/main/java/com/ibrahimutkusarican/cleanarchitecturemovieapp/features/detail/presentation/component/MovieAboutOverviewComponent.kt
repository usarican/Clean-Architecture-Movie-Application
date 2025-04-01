package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailAboutModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.mockMovieDetailModel

@Composable
fun MovieAboutOverviewComponent(
    movieDetailAboutModel: MovieDetailAboutModel
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
    ) {
        Text(
            text = stringResource(R.string.synopsis),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = movieDetailAboutModel.overview,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W400)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieOverviewSection() {
    MovieAboutOverviewComponent(
        movieDetailAboutModel = mockMovieDetailModel.movieDetailAboutModel
    )
}
