package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailAboutModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.mockMovieDetailModel

@Composable
fun MovieAboutInformationComponent(
    movieDetailAboutModel: MovieDetailAboutModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MovieInformationItem(
            value = movieDetailAboutModel.rating,
            label = stringResource(R.string.rating)
        )
        MovieInformationItem(
            value = movieDetailAboutModel.fullReleaseDate,
            label = stringResource(R.string.release)
        )
        movieDetailAboutModel.revenue?.let {
            MovieInformationItem(
                value = it,
                label = stringResource(R.string.revenue)
            )
        }
        movieDetailAboutModel.budget?.let {
            MovieInformationItem(
                value = it,
                label = stringResource(R.string.budget)
            )
        }
    }
}

@Composable
private fun MovieInformationItem(value: String, label: String) {
    Column {
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.outline
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieAboutInformationComponent() {
    MovieAboutInformationComponent(
        movieDetailAboutModel = mockMovieDetailModel.movieDetailAboutModel
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieInformationItem() {
    MovieInformationItem(
        value = "1h 30min",
        label = "Duration"
    )
}