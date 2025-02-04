package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.carouselTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage
import kotlinx.coroutines.delay

@Composable
@Preview(showBackground = true)
fun BannerMoviesScreen(
    clickItemIndex: Int = 0
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val movies by viewModel.movies.collectAsStateWithLifecycle()
    val pagerState: PagerState =
        rememberPagerState(pageCount = { movies[MovieType.NOW_PLAYING]?.size ?: 0 })
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val topMargin = dimensionResource(R.dimen.dp_64)


    LaunchedEffect(movies) {
        movies[MovieType.NOW_PLAYING]?.let { nowPlayingMovies ->
            if (nowPlayingMovies.isNotEmpty()) {
                pagerState.animateScrollToPage(clickItemIndex)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight / 2) + topMargin)
        ) {
            MovieImage(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(3.dp)
                    .padding(bottom = dimensionResource(R.dimen.dp_64)),
                imageUrl = movies[MovieType.NOW_PLAYING]?.get(pagerState.currentPage)?.movieBackdropImageUrl,
                contentScale = ContentScale.Crop
            )
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dimensionResource(R.dimen.dp_80)),
                state = pagerState,
                contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.x_x_x_large_padding)),
                pageSpacing = dimensionResource(R.dimen.large_padding)
            ) { page ->
                movies[MovieType.NOW_PLAYING]?.get(page)?.let { movie ->
                    BannerMovie(
                        modifier = Modifier
                            .carouselTransition(page = page, pagerState = pagerState),
                        bannerMovie = movie,
                        isSelected = pagerState.currentPage == page,
                    )

                }
            }
        }
        movies[MovieType.NOW_PLAYING]?.get(pagerState.currentPage)
            ?.let {
                BannerMovieInfo(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = dimensionResource(R.dimen.x_x_x_large_padding),
                            vertical = dimensionResource(R.dimen.x_large_padding)
                        ), bannerMovie = it, seeDetailClickAction = {})
            }
    }
}

@Composable
private fun BannerMovieInfo(
    bannerMovie: BasicMovieModel,
    seeDetailClickAction: (movieId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding))
    ) {
        Text(
            text = bannerMovie.movieTitle, style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            ),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = bannerMovie.releaseDate, style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.six_padding)),
                text = "•",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Icon(
                Icons.Default.Star,
                contentDescription = "Star",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                modifier = Modifier.padding(start = dimensionResource(R.dimen.three_dp)),
                text = "${bannerMovie.movieVotePoint} / 10",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
        MovieGenreView(modifier = Modifier.weight(1f),genreList = bannerMovie.movieGenres)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.large_padding)),
            shape = RoundedCornerShape(dimensionResource(R.dimen.small_border)),
            onClick = {
                seeDetailClickAction(bannerMovie.movieId)
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = dimensionResource(R.dimen.x_small_padding),
                        horizontal = dimensionResource(R.dimen.small_padding)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.see_more),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.weight(1f)
                )
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                ) {
                    Icon(
                        modifier = Modifier.padding(dimensionResource(R.dimen.x_small_padding)),
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MovieGenreView(modifier: Modifier = Modifier, genreList: List<String>) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
        maxItemsInEachRow = 3
    ) {
        for (index in 0 until genreList.size) {
            MovieGenreItem(genreText = genreList[index], index)
        }
    }
}

@Composable
private fun MovieGenreItem(genreText: String, index: Int) {
    Card(
        modifier = Modifier.padding(
            start = if (index % 3 != 0) dimensionResource(R.dimen.small_padding) else dimensionResource(
                R.dimen.zero_dp
            )
        ),
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
            ), text = genreText, style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.W700
            )
        )

    }
}

@Composable
fun BannerMovie(
    modifier: Modifier = Modifier,
    bannerMovie: BasicMovieModel,
    isSelected: Boolean
) {
    val animatedElevation by animateDpAsState(
        targetValue = if (isSelected) 4.dp else 0.dp,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    Card(
        elevation = CardDefaults.cardElevation(animatedElevation),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        MovieImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.x_small_border))),
            imageUrl = bannerMovie.moviePosterImageUrl,
            contentScale = ContentScale.FillBounds
        )
    }
}