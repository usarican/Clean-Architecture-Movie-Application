package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailRecommendedModelItem
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.mockMovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.action.DetailUiAction
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper.fontDimensionResource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.widget.MovieImage

@Composable
fun MovieDetailRecommendScreen(
    movieDetailModel: MovieDetailModel,
    handleUiAction: (action: DetailUiAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.medium_padding))
            .padding(bottom = dimensionResource(R.dimen.medium_padding)),
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.recommended_for_you),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W700
                )
            )
            Text(
                modifier = Modifier.clickable {
                    handleUiAction(
                        DetailUiAction.SeeAllClickAction(
                            SeeAllType.RecommendationMovies(
                                movieDetailModel.movieDetailInfoModel.movieId
                            )
                        )
                    )
                },
                text = stringResource(R.string.see_all),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(R.dimen.medium_padding)),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
        ) {
            items(movieDetailModel.movieDetailRecommendedMovies.recommendedMovies) { model ->
                RecommendedMovieItem(
                    recommendedMovieModel = model,
                    movieClickAction = {
                        handleUiAction(DetailUiAction.RecommendedMovieClickAction(model.movieId))
                    }
                )
            }
        }
    }
}

@Composable
private fun RecommendedMovieItem(
    recommendedMovieModel: MovieDetailRecommendedModelItem,
    movieClickAction: (movieId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(dimensionResource(R.dimen.home_category_movie_width))
            .clickable {
                movieClickAction(recommendedMovieModel.movieId)
            },
    ) {
        Card(
            modifier = Modifier
                .height(dimensionResource(R.dimen.home_category_movie_height))
                .width(dimensionResource(R.dimen.home_category_movie_width)),
            shape = RoundedCornerShape(dimensionResource(R.dimen.s_medium_border)),
        ) {
            MovieImage(
                imageUrl = recommendedMovieModel.moviePosterImageUrl
            )
        }
        Text(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.x_small_padding)),
            text = recommendedMovieModel.movieTitle,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = fontDimensionResource(R.dimen.movie_category_item_title_size),
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (recommendedMovieModel.movieGenres.isNotEmpty()) {
            Text(
                text = recommendedMovieModel.movieGenres.first(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieDetailRecommendedScreen() {
    MovieDetailRecommendScreen(
        movieDetailModel = mockMovieDetailModel
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun PreviewRecommendedMovieItem() {
    RecommendedMovieItem(
        recommendedMovieModel = mockMovieDetailModel.movieDetailRecommendedMovies.recommendedMovies.first()
    ) {}
}