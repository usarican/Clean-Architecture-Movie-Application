package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
fun UpcomingMoviesScreen(modifier: Modifier = Modifier, homeMovieModels: List<HomeMovieModel>) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.up_coming_movies),
            style = MaterialTheme.typography.titleLarge
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = spacedBy(4.dp),
        ) {
            items(homeMovieModels) { popularMovie ->
                UpComingMovieItem(upComingMovie = popularMovie)
            }
        }
    }
}

@Composable
fun UpComingMovieItem(upComingMovie : HomeMovieModel) {
    Card(
        modifier = Modifier
            .height(dimensionResource(R.dimen.home_category_movie_height))
            .width(dimensionResource(R.dimen.home_category_movie_width)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_corner)),
    ) {
        Box {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                MovieImage(
                    modifier = Modifier.fillMaxSize(),
                    imageUrl = upComingMovie.moviePosterImageUrl
                )
                Text(text = upComingMovie.movieTitle, style = MaterialTheme.typography.titleSmall)
            }
        }
    }
}