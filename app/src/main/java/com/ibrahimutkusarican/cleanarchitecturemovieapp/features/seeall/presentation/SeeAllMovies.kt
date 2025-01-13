package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.LoadingScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.convertMovieException
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.fontDimensionResource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
fun SeeAllMovies(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<SeeAllMovieModel>,
    cachedMovies: List<SeeAllMovieModel>
) {
    when (pagingMovies.loadState.refresh) {
        is LoadState.Error -> {
            ErrorScreen(exception = (pagingMovies.loadState.refresh as LoadState.Error).error.convertMovieException())
        }

        LoadState.Loading -> LoadingScreen()
        is LoadState.NotLoading -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = dimensionResource(R.dimen.large_padding)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
                ) {
                items(pagingMovies.itemCount) { index ->
                    pagingMovies[index]?.let { movie ->
                        SeeAllMovieItem(
                            seeAllMovie = movie
                        )
                    }
                }

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SeeAllMovieItem(
    modifier: Modifier = Modifier, seeAllMovie: SeeAllMovieModel = SeeAllMovieModel(
        movieId = 0,
        movieTitle = "Spiderman No Way Home",
        movieContent = LoremIpsum(50).values.joinToString(),
        moviePosterImageUrl = "",
        movieGenres = listOf("Action", "Scintfic", "Drama"),
        movieTMDBScore = 7.4,
        movieReleaseTime = "2022-12-15"
    )
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.small_card_elevation))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.see_all_category_movie_width))
        ) {
            Card(
                shape = RoundedCornerShape(dimensionResource(R.dimen.medium_border))
            ) {
                MovieImage(
                    imageUrl = seeAllMovie.moviePosterImageUrl,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.see_all_category_movie_width))
                        .height(dimensionResource(R.dimen.see_all_category_movie_width))
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = dimensionResource(R.dimen.medium_padding)),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = seeAllMovie.movieTitle,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = fontDimensionResource(R.dimen.movie_category_item_title_size)
                    )
                )
                Text(
                    text = seeAllMovie.movieContent,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = fontDimensionResource(R.dimen.see_all_movie_item_content_text_size)
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}
