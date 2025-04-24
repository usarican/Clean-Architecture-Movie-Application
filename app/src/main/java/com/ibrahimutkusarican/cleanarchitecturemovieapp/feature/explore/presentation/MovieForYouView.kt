package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import com.iusarican.common.utils.Constants.EMPTY_STRING
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.widget.MovieImage

@Composable
@Preview(showBackground = true)
fun ExploreForYouMovieView(
    modifier: Modifier = Modifier, movie: BasicMovieModel = BasicMovieModel(
        movieId = 1,
        movieTitle = "The Batman",
        movieGenres = listOf("Action", "Drama", "Scientfic"),
        releaseDate = "2022-03-01",
        movieOverview = LoremIpsum(100).values.joinToString(),
        moviePosterImageUrl = null,
        movieBackdropImageUrl = null,
        movieVotePoint = "8.2"
    ),
    handleUiAction: (action: ExploreUiAction) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .clickable { handleUiAction(ExploreUiAction.ForYouMovieClickAction(movie.movieId)) }
    ) {
        Text(
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.small_padding)),
            text = stringResource(R.string.for_you),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimensionResource(R.dimen.s_medium_border)),
            elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.small_card_elevation)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.small_padding))
            ) {
                Row(
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.small_padding))
                ) {
                    Card(
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.home_category_movie_height))
                            .width(dimensionResource(R.dimen.explore_for_you_movie_width)),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.s_medium_border)),
                    ) {
                        MovieImage(
                            imageUrl = movie.moviePosterImageUrl
                        )
                    }
                    Column(
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.home_category_movie_height))
                            .padding(
                                start = dimensionResource(R.dimen.small_padding),
                                top = dimensionResource(R.dimen.large_padding)
                            ),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
                    ) {
                        TextWithIcon(
                            icon = painterResource(R.drawable.ic_calender), text = movie.releaseDate
                        )
                        TextWithIcon(
                            icon = painterResource(R.drawable.ic_vote_count),
                            text = movie.movieVotePoint
                        )
                        ForYouMovieGenreView(
                            modifier = Modifier.weight(1f), genreList = movie.movieGenres
                        )
                    }
                }
                Text(
                    text = movie.movieTitle,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = movie.movieOverview,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }

}

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier, icon: Painter, text: String
) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier,
            painter = icon, tint = MaterialTheme.colorScheme.outline, contentDescription = "text"
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = dimensionResource(R.dimen.small_padding)),
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ForYouMovieGenreView(modifier: Modifier = Modifier, genreList: List<String>) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
        maxItemsInEachRow = 4
    ) {
        for (index in 0 until genreList.size + 1) {
            ForYouMovieGenreItem(
                showIcon = index == 0,
                genreText = if (index == 0) EMPTY_STRING else genreList[index - 1]
            )
        }
    }
}

@Composable
fun ForYouMovieGenreItem(showIcon: Boolean = false, genreText: String) {
    if (showIcon) {
        Icon(
            modifier = Modifier,
            painter = painterResource(R.drawable.ic_genre),
            tint = MaterialTheme.colorScheme.outline,
            contentDescription = "text"
        )
    } else {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            border = BorderStroke(
                dimensionResource(R.dimen.one_dp), color = MaterialTheme.colorScheme.onSurface
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.x_small_border)),
        ) {
            Text(
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.x_small_padding),
                    horizontal = dimensionResource(R.dimen.small_padding)
                ), text = genreText, style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

        }
    }
}