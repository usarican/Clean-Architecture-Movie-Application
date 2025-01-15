package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.carouselTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
fun ExploreBannerMovies(
    modifier : Modifier = Modifier,
    bannerMovies : List<BasicMovieModel>
) {
    val state = rememberPagerState { bannerMovies.size }
    HorizontalPager(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.forty_dp)),
        pageSpacing = dimensionResource(R.dimen.twelve_padding),
    ) { page ->
        ExploreBannerMovieItem(
            modifier = Modifier.carouselTransition(startValue = 0.9F, page = page, pagerState = state),
            bannerMovie = bannerMovies[page],
            isSelected = state.currentPage == page
        )
    }
}


@Composable
fun ExploreBannerMovieItem(
    modifier : Modifier = Modifier,
    bannerMovie : BasicMovieModel,
    isSelected : Boolean
){
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

