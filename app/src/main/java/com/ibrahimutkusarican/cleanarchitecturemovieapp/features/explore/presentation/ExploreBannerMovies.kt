package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.BANNER_EXPLORE_PAGER_DOT_INDICATOR_COUNT
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.carouselTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
fun ExploreBannerMovies(
    modifier: Modifier = Modifier, bannerMovies: List<BasicMovieModel>
) {
    val state = rememberPagerState { bannerMovies.size }
    HorizontalPager(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.forty_dp)),
        pageSpacing = dimensionResource(R.dimen.twelve_padding),
    ) { page ->
        ExploreBannerMovieItem(
            modifier = Modifier.carouselTransition(
                startValue = 0.85F, page = page, pagerState = state
            ), bannerMovie = bannerMovies[page], isSelected = state.currentPage == page
        )
    }
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))

    DotIndicator(state)
}

@Composable
private fun DotIndicator(state: PagerState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(BANNER_EXPLORE_PAGER_DOT_INDICATOR_COUNT) { index ->
            val isSelected = (state.currentPage % 3 == index)

            val dotWidth by animateDpAsState(
                targetValue = if (isSelected) dimensionResource(R.dimen.pager_indicator_big_dot_width) else dimensionResource(
                    R.dimen.pager_indicator_dot_width
                ), label = "DotWidth"
            )

            val dotColor by animateColorAsState(
                targetValue = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                label = "DotColor"
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .height(dimensionResource(R.dimen.pager_indicator_dot_height))
                    .width(dotWidth)
                    .clip(CircleShape)
                    .background(dotColor)
            )
        }
    }
}


@Composable
fun ExploreBannerMovieItem(
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
                .fillMaxSize(),
            imageUrl = bannerMovie.moviePosterImageUrl,
            contentScale = ContentScale.FillBounds
        )
    }
}

