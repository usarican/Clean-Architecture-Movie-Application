package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.carouselTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
fun BannerMoviesScreen(
    modifier: Modifier = Modifier,
    homeMovieModels: List<HomeMovieModel>
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { homeMovieModels.size })

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
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
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
}
