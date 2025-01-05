package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageUrlHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getStringRes
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.paddingWithMovieItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.showMovieItemText
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.fontDimensionResource

@Composable
fun MovieCategoryList(
    modifier: Modifier, movies: Map<MovieType, List<HomeMovieModel>>
) {
    MovieCategory(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        movieType = MovieType.POPULAR,
        movies = movies[MovieType.POPULAR]
    )
    MovieCategory(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        movieType = MovieType.TOP_RATED,
        movies = movies[MovieType.TOP_RATED]
    )
    MovieCategory(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        movieType = MovieType.UPCOMING,
        movies = movies[MovieType.UPCOMING]
    )
}


@Composable
fun MovieCategory(
    modifier: Modifier, movieType: MovieType, movies: List<HomeMovieModel>?
) {
    if (movies != null) {
        Column(
            modifier = modifier
        ) {
            Text(
                text = stringResource(movieType.getStringRes()),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.small_padding)),
                horizontalArrangement = spacedBy(dimensionResource(movieType.paddingWithMovieItems())),
            ) {
                items(movies) { movie ->
                    MovieCategoryItemList(
                        movie = movie, showItemWithText = movieType.showMovieItemText()
                    )
                }
            }
        }
    }

}

@Composable
fun MovieCategoryItemList(movie: HomeMovieModel, showItemWithText: Boolean) {
    if (showItemWithText) {
        MovieCategoryItemWithText(movie)
    } else {
        MovieCategoryItemWithoutText(movie)
    }
}

@Composable
fun MovieCategoryItemWithText(
    categoryMovieItem: HomeMovieModel
) {
    Column(
        modifier = Modifier.width(dimensionResource(R.dimen.home_category_movie_width)),
    ) {
        Card(
            modifier = Modifier
                .height(dimensionResource(R.dimen.home_category_movie_height))
                .width(dimensionResource(R.dimen.home_category_movie_width)),
            shape = RoundedCornerShape(dimensionResource(R.dimen.s_medium_border)),
        ) {
            MovieImage(
                imageUrl = categoryMovieItem.moviePosterImageUrl
            )
        }
        Text(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.x_small_padding)),
            text = categoryMovieItem.movieTitle,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = fontDimensionResource(R.dimen.movie_category_item_title_size),
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (categoryMovieItem.movieGenres.isNotEmpty()) {
            Text(
                text = categoryMovieItem.movieGenres.first(),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.scrim
                )
            )
        }
    }
}

@Composable
fun MovieCategoryItemWithoutText(
    categoryMovieItem: HomeMovieModel
) {
    Column {
        Card(
            modifier = Modifier
                .height(dimensionResource(R.dimen.home_category_movie_height))
                .width(dimensionResource(R.dimen.home_category_movie_width)),
            shape = RoundedCornerShape(dimensionResource(R.dimen.small_border)),
        ) {
            MovieImage(
                imageUrl = categoryMovieItem.moviePosterImageUrl
            )
        }

    }
}
