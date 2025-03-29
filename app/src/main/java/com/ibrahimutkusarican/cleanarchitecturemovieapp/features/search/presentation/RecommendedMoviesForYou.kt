package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation.MovieCategoryItemList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RecommendedMoviesForYou(
    modifier: Modifier = Modifier,
    movies: List<BasicMovieModel>,
    seeAllClickAction: () -> Unit = {},
    movieClickAction: (movieId: Int,sharedAnimationKey : String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.recommended_for_you),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(
                modifier = Modifier.clickable(enabled = true, onClick = {
                    seeAllClickAction.invoke()
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
                    movieClickAction = movieClickAction,
                    movie = movie,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope
                )
            }
        }
    }
}