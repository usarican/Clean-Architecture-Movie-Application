package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    clickItemIndex: Int = 0
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val movies by viewModel.movies.collectAsStateWithLifecycle()
    val pagerState: PagerState =
        rememberPagerState(pageCount = { movies[MovieType.UPCOMING]?.size ?: 0 })
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp


    LaunchedEffect(Unit) {
        pagerState.animateScrollToPage(clickItemIndex)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight / 2)) {
            MovieImage(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(3.dp)
                    .padding(bottom = dimensionResource(R.dimen.x_large_padding)),
                imageUrl = movies[MovieType.UPCOMING]?.get(pagerState.currentPage)?.movieBackdropImageUrl,
                contentScale = ContentScale.Crop
            )
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 2),
                state = pagerState,
                contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.x_x_x_large_padding)),
                pageSpacing = dimensionResource(R.dimen.large_padding)
            ) { page ->
                movies[MovieType.UPCOMING]?.get(page)?.let {
                    BannerMovie(
                        modifier = Modifier
                            .padding(top = dimensionResource(R.dimen.dp_64))
                            .carouselTransition(page = page, pagerState = pagerState),
                        bannerMovie = it,
                        isSelected = pagerState.currentPage == page,
                    )

                }
            }
        }
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