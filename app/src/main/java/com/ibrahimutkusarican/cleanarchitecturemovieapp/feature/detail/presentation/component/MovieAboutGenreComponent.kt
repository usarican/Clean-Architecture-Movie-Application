package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailAboutModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.mockMovieDetailModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieAboutGenreComponent(
    movieDetailAboutModel: MovieDetailAboutModel
) {
    Column {
        Text(
            text = stringResource(R.string.genres),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.small_padding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
        ) {
            movieDetailAboutModel.genres.forEach { genre ->
                MovieGenreItem(genreText = genre)
            }
        }
    }
}

@Composable
private fun MovieGenreItem(genreText: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(
            dimensionResource(R.dimen.one_dp), color = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_border)),
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = dimensionResource(R.dimen.small_padding),
                horizontal = dimensionResource(R.dimen.twelve_padding)
            ), text = genreText, style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieGenreSection() {
    MovieAboutGenreComponent(
        movieDetailAboutModel = mockMovieDetailModel.movieDetailAboutModel
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieGenreItem() {
    MovieGenreItem(genreText = "Action")
}