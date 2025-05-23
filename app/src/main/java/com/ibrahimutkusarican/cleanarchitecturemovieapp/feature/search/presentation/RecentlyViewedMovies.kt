package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.presentation.SeeAllMovieItem

@Composable
fun RecentlyViewedMovies(
    modifier: Modifier = Modifier,
    movies: List<SeeAllMovieModel>,
    movieClickAction: (movieId: Int) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.recently_viewed),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(top = dimensionResource(R.dimen.small_padding)),
            verticalArrangement = spacedBy(dimensionResource(R.dimen.medium_padding)),
        ) {
            movies.forEach { movie ->
                SeeAllMovieItem(
                    movieClickAction = movieClickAction,
                    seeAllMovie = movie
                )
            }
        }
    }
}