package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.carouselTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
@Preview(showBackground = true)
fun BannerMoviesScreen(
    clickItemIndex: Int = 0,
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
            IconButton(onClick = {
                viewModel.handleUiAction(HomeUiAction.BannerMovieOnBackPress)
            },
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .align(Alignment.TopStart)
                    .padding(start = dimensionResource(R.dimen.small_padding))
                    .zIndex(2F)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                )) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }

            AnimatedMovieBackdrop(
                modifier = Modifier, pagerState = pagerState, movies = movies
            )
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dimensionResource(R.dimen.dp_80)),
                state = pagerState,
                contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.dp_64)),
                pageSpacing = dimensionResource(R.dimen.large_padding)
            ) { page ->
                movies[MovieType.NOW_PLAYING]?.get(page)?.let { movie ->
                    BannerMovie(
                        modifier = Modifier.carouselTransition(
                                page = page,
                                pagerState = pagerState
                            ),
                        bannerMovie = movie,
                        isSelected = pagerState.currentPage == page,
                    )

                }
            }
        }
        movies[MovieType.NOW_PLAYING]?.get(pagerState.currentPage)?.let {
                BannerMovieInfo(modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = dimensionResource(R.dimen.dp_64),
                        vertical = dimensionResource(R.dimen.x_large_padding)
                    ), bannerMovie = it, seeMoreClickAction = { movieId ->
                    viewModel.handleUiAction(HomeUiAction.MovieClickAction(movieId))
                })
            }
    }
}

@Composable
private fun BannerMovieInfo(
    bannerMovie: BasicMovieModel,
    seeMoreClickAction: (movieId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding))
    ) {
        // Animated Title Transition
        AnimatedContent(
            targetState = bannerMovie.movieTitle, transitionSpec = {
                fadeIn(animationSpec = tween(500)) togetherWith fadeOut(animationSpec = tween(500))
            }, label = "MovieTitleAnimation"
        ) { title ->
            Text(
                text = title, style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground
                ), textAlign = TextAlign.Center
            )
        }

        // Animated Row for Release Date and Rating
        AnimatedContent(
            targetState = bannerMovie.releaseDate to bannerMovie.movieVotePoint, transitionSpec = {
                slideInVertically(initialOffsetY = { it }) togetherWith slideOutVertically(
                    targetOffsetY = { -it })
            }, label = "MovieDetailsAnimation"
        ) { (releaseDate, rating) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = releaseDate, style = MaterialTheme.typography.bodyMedium.copy(
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
                    text = "$rating / 10",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }

        // Animate Genre List
        AnimatedContent(
            targetState = bannerMovie.movieGenres, transitionSpec = {
                fadeIn(animationSpec = tween(500)) togetherWith fadeOut(animationSpec = tween(500))
            }, label = "MovieGenresAnimation"
        ) { genres ->
            MovieGenreView(modifier = Modifier.weight(1f), genreList = genres)
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.large_padding)),
            shape = RoundedCornerShape(dimensionResource(R.dimen.small_border)),
            onClick = {
                seeMoreClickAction(bannerMovie.movieId)
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
                    ), verticalAlignment = Alignment.CenterVertically
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
        for (index in genreList.indices) {
            MovieGenreItem(genreText = genreList[index])
        }
    }
}

@Composable
private fun MovieGenreItem(genreText: String) {
    Card(
        modifier = Modifier.padding(
            start = dimensionResource(R.dimen.small_padding)
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
fun AnimatedMovieBackdrop(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    movies: Map<MovieType, List<BasicMovieModel>>
) {
    val currentMovieImage =
        movies[MovieType.NOW_PLAYING]?.get(pagerState.currentPage)?.movieBackdropImageUrl

    AnimatedContent(
        targetState = currentMovieImage, transitionSpec = {
            (slideInHorizontally(initialOffsetX = { fullWidth -> if (pagerState.currentPageOffsetFraction > 0) -fullWidth else fullWidth }) + fadeIn()).togetherWith(
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> if (pagerState.currentPageOffsetFraction > 0) fullWidth else -fullWidth }) + fadeOut()
            )
        }, label = "movieImageTransition"
    ) { imageUrl ->
        MovieImage(
            modifier = modifier
                .fillMaxSize()
                .blur(3.dp)
                .padding(bottom = dimensionResource(R.dimen.dp_64)),
            imageUrl = imageUrl,
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun BannerMovie(
    modifier: Modifier = Modifier, bannerMovie: BasicMovieModel, isSelected: Boolean
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