package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.paging.compose.LazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.BasePagingComposable
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.fontDimensionResource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
fun MyListPageScreen(movies : LazyPagingItems<MyListMovieModel>){
    BasePagingComposable(movies) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(R.dimen.large_padding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
        ) {
            items(count = movies.itemCount,
                key = { index -> index }) { index ->
                movies[index]?.let { movie ->
                    MyListMovieItem(
                        myListMovie = movie
                    )
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun MyListMovieItem(
    modifier: Modifier = Modifier,
    myListMovie: MyListMovieModel = MyListMovieModel(
        movieId = 0,
        title = "Spiderman No Way Home",
        overview = LoremIpsum(50).values.joinToString(),
        posterPath = "",
        genres = listOf("Action", "Scintfic", "Drama"),
        movieRating = "7.4",
        releaseDate = "2022-12-15",
        isAddedWatchList = true,
        isFavorite = false
    ),
    movieClickAction : (movieId : Int) -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth().clickable {
            movieClickAction.invoke(myListMovie.movieId)
        }, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
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
                    imageUrl = myListMovie.posterPath,
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
                    text = myListMovie.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = fontDimensionResource(R.dimen.movie_category_item_title_size),
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = myListMovie.overview,
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
