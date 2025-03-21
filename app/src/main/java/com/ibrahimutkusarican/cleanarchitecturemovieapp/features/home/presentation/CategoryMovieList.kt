package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getStringRes
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.fontDimensionResource

@Composable
fun MovieCategoryList(
    modifier: Modifier,
    movies: Map<MovieType, List<BasicMovieModel>>,
    seeAllClickAction: (seeAllType: SeeAllType) -> Unit,
    movieClickAction: (movieId: Int) -> Unit
) {
    MovieCategory(
        modifier = modifier.wrapContentHeight(),
        seeAllMovieType = SeeAllType.SeeAllMovieType.Popular,
        movies = movies[MovieType.POPULAR],
        seeAllClickAction = seeAllClickAction,
        movieClickAction = movieClickAction
    )
    MovieCategory(
        modifier = modifier.wrapContentHeight(),
        seeAllMovieType = SeeAllType.SeeAllMovieType.TopRated,
        movies = movies[MovieType.TOP_RATED],
        seeAllClickAction = seeAllClickAction,
        movieClickAction = movieClickAction
    )
    MovieCategory(
        modifier = modifier.wrapContentHeight(),
        seeAllMovieType = SeeAllType.SeeAllMovieType.UpComing,
        movies = movies[MovieType.UPCOMING],
        seeAllClickAction = seeAllClickAction,
        movieClickAction = movieClickAction
    )
}


@Composable
fun MovieCategory(
    modifier: Modifier,
    seeAllMovieType: SeeAllType.SeeAllMovieType,
    movies: List<BasicMovieModel>?,
    title: String? = null,
    seeAllClickAction: (SeeAllType.SeeAllMovieType) -> Unit,
    movieClickAction: (movieId: Int) -> Unit = {}
) {
    if (movies != null) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title ?: stringResource(seeAllMovieType.movieType.getStringRes()),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    modifier = Modifier.clickable(enabled = true, onClick = {
                        seeAllClickAction.invoke(seeAllMovieType)
                    }),
                    text = stringResource(R.string.see_all),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary
                    ),
                )
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.small_padding)),
                horizontalArrangement = spacedBy(dimensionResource(R.dimen.medium_padding)),
            ) {
                items(movies) { movie ->
                    MovieCategoryItemList(
                        movie = movie,
                        movieClickAction = movieClickAction
                    )
                }
            }
        }
    }

}

@Composable
fun MovieCategoryItemList(
    modifier: Modifier = Modifier,
    movie: BasicMovieModel,
    movieClickAction: (movieId: Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .width(dimensionResource(R.dimen.home_category_movie_width))
            .clickable {
                movieClickAction(movie.movieId)
            },
    ) {
        Card(
            modifier = Modifier
                .height(dimensionResource(R.dimen.home_category_movie_height))
                .width(dimensionResource(R.dimen.home_category_movie_width)),
            shape = RoundedCornerShape(dimensionResource(R.dimen.s_medium_border)),
        ) {
            MovieImage(
                imageUrl = movie.moviePosterImageUrl
            )
        }
        Text(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.x_small_padding)),
            text = movie.movieTitle,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = fontDimensionResource(R.dimen.movie_category_item_title_size),
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (movie.movieGenres.isNotEmpty()) {
            Text(
                text = movie.movieGenres.first(), style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
