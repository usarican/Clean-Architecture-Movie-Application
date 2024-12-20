package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.BANNER_MOVIES_AUTO_SCROLL_TIME
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.carouselTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BannerMoviesScreen(
    modifier: Modifier = Modifier,
    homeMovieModels: List<HomeMovieModel>
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { homeMovieModels.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            var currentPageKey by remember { mutableIntStateOf(0) }
            LaunchedEffect(key1 = currentPageKey) {
                launch {
                    delay(timeMillis = BANNER_MOVIES_AUTO_SCROLL_TIME)
                    val nextPage = (currentPage + 1).mod(pageCount)
                    animateScrollToPage(page = nextPage)
                    currentPageKey = nextPage
                }
            }
        }
    }

    HorizontalPager(
        modifier = modifier, state = pagerState,
        contentPadding = PaddingValues(horizontal = 32.dp),
        pageSpacing = 16.dp
    ) { page ->
        BannerMovieItem(
            modifier = modifier.carouselTransition(page, pagerState),
            homeMovieModels[page]
        )
    }
}

@Composable
fun BannerMovieItem(modifier: Modifier = Modifier, bannerMovie: HomeMovieModel) {
    Box(
        modifier = modifier
    ) {
        MovieImage(
            modifier = modifier.fillMaxSize(),
            imageUrl = bannerMovie.moviePosterImageUrl
        )
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = bannerMovie.movieTitle,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
