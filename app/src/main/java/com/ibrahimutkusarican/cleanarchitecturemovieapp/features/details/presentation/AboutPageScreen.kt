package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.CastModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailAboutModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.mockMovieDetailModel

@Composable
@Preview(showBackground = true)
fun AboutPageScreen(
    modifier: Modifier = Modifier,
    movieDetailAboutModel: MovieDetailAboutModel = mockMovieDetailModel.movieDetailAboutModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.large_padding))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding))
    ) {
        MovieBudgetDurationInformationSection(movieDetailAboutModel = movieDetailAboutModel)
        MovieGenreSection(movieDetailAboutModel = movieDetailAboutModel)
        MovieOverviewSection(movieDetailAboutModel = movieDetailAboutModel)
        MovieCastSection(movieDetailAboutModel = movieDetailAboutModel)
    }
}


@Composable
private fun MovieBudgetDurationInformationSection(
    modifier: Modifier = Modifier, movieDetailAboutModel: MovieDetailAboutModel
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MovieInformationItem(
            value = movieDetailAboutModel.rating,
            label = stringResource(R.string.rating)
        )
        MovieInformationItem(
            value = movieDetailAboutModel.fullReleaseDate, label = stringResource(R.string.release)
        )
        movieDetailAboutModel.revenue?.let {
            MovieInformationItem(
                value = it, label = stringResource(R.string.revenue)
            )
        }
        movieDetailAboutModel.budget?.let {
            MovieInformationItem(
                value = it, label = stringResource(R.string.budget)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MovieInformationItem(
    value: String = "1h 30min", label: String = "Duration"
) {
    Column {
        Text(
            text = value, style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            text = label, style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.outline
            )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MovieGenreSection(movieDetailAboutModel: MovieDetailAboutModel) {
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

@Composable
private fun MovieOverviewSection(movieDetailAboutModel: MovieDetailAboutModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
    ) {
        Text(
            text = stringResource(R.string.synopsis),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = movieDetailAboutModel.overview, style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W400
            )
        )
    }
}

@Composable
private fun MovieCastSection(
    modifier: Modifier = Modifier,
    movieDetailAboutModel: MovieDetailAboutModel
) {
    Column {
        Text(
            text = stringResource(R.string.cast), style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.small_padding),
                    bottom = dimensionResource(R.dimen.medium_padding)
                ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.twelve_padding))
        ) {
            items(movieDetailAboutModel.casts) { cast ->
                MovieCastItem(castModel = cast)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MovieCastItem(
    modifier: Modifier = Modifier,
    castModel: CastModel = mockMovieDetailModel.movieDetailAboutModel.casts.first()
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.cast_card_radius)),
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.six_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CastImage(imageUrl = castModel.profileImage)
            Column(
                modifier = modifier.padding(horizontal = dimensionResource(R.dimen.six_padding))
            ) {
                Text(
                    text = castModel.characterName, style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                castModel.originalName?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Light
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun CastImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    Card(
        modifier = modifier.size(dimensionResource(R.dimen.cast_image_size)),
        shape = CircleShape
    ) {
        AsyncImage(
            modifier = Modifier,
            placeholder = painterResource(R.drawable.ic_no_image_person),
            model = imageUrl,
            contentDescription = "CastImage",
            contentScale = contentScale,
        )
    }
}

