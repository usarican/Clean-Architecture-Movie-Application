package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.paging.compose.LazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.EmptyScreenType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.base.BasePagingComposable
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper.fontDimensionResource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.widget.MovieImage

@Composable
fun SearchedMoviesList(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<SeeAllMovieModel>,
    handleUiAction: (uiAction: SearchUiAction) -> Unit,
    emptyScreenType: EmptyScreenType
) {
    BasePagingComposable(
        pagingItems = pagingMovies,
        emptyScreenType = emptyScreenType
    ) {
        LaunchedEffect(Unit) {
            handleUiAction(
                SearchUiAction.AddLastSearchedText
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(R.dimen.large_padding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.twelve_padding)),
        ) {
            items(count = pagingMovies.itemCount,
                key = { index -> index }) { index ->
                pagingMovies[index]?.let { movie ->
                    SearchMovieItem(
                        movie = movie,
                        movieClickAction = { movieId ->
                            handleUiAction.invoke(SearchUiAction.MovieClick(movieId))
                        }
                    )
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchMovieItem(
    modifier: Modifier = Modifier,
    movie: SeeAllMovieModel = SeeAllMovieModel(
        movieId = 0,
        movieTitle = "Spiderman No Way Home",
        movieContent = LoremIpsum(50).values.joinToString(),
        moviePosterImageUrl = "",
        movieGenres = listOf("Action", "Scintfic", "Drama"),
        movieTMDBScore = 7.4,
        movieReleaseTime = "2022-12-15"
    ),
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
