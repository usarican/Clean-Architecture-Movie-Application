package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
fun PopularMoviesScreen(
    modifier: Modifier = Modifier,
    homeMovieModels: List<HomeMovieModel>
) {
    Column(
        modifier = modifier
    ) {
        Text(text = stringResource(R.string.popular_movies), style = MaterialTheme.typography.titleMedium)
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = spacedBy(4.dp),
        ) {
            items(homeMovieModels){ popularMovie ->
                UpComingMovieItem(upComingMovie = popularMovie)
            }
        }
    }
}

@Composable
fun PopularMovieItem(popularMovie: HomeMovieModel) {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                MovieImage(
                    modifier = Modifier.fillMaxSize(),
                    imageUrl = popularMovie.moviePosterImageUrl
                )
                Text(text = popularMovie.movieTitle, style = MaterialTheme.typography.titleSmall)
            }
        }
    }
}
