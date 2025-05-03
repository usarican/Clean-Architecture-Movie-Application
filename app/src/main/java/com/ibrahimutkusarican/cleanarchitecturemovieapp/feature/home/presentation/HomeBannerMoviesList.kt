package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.presentation


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
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
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import com.iusarican.common.utils.extension.blurTransition
import com.iusarican.common.utils.extension.carouselTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.widget.MovieImage

@Composable
fun HomeBannerMoviesList(
    modifier: Modifier = Modifier, basicMovieModels: List<BasicMovieModel>,
    movieClickAction : (movieIndex : Int) -> Unit
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { basicMovieModels.size })

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.x_x_x_large_padding)),
        pageSpacing = dimensionResource(R.dimen.large_padding)
    ) { page ->
        BannerMovieItem(
            modifier = modifier.carouselTransition(page = page, pagerState =  pagerState),
            bannerMovie = basicMovieModels[page],
            page = page,
            pagerState = pagerState,
            isSelected = pagerState.currentPage == page,
            movieClickAction = movieClickAction
        )
    }
}


@Composable
fun BannerMovieItem(
    modifier: Modifier = Modifier,
    bannerMovie: BasicMovieModel,
    page: Int,
    pagerState: PagerState,
    isSelected: Boolean,
    movieClickAction : (movieIndex : Int) -> Unit
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
                .blurTransition(page, pagerState)
                .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.x_small_border)))
                .clickable {
                    movieClickAction(page)
                },
            imageUrl = bannerMovie.moviePosterImageUrl,
            contentScale = ContentScale.FillBounds
        )
    }
}
