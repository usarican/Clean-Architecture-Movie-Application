package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.blurTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.carouselTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
fun BannerMoviesList(
    modifier: Modifier = Modifier, homeMovieModels: List<HomeMovieModel>
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { homeMovieModels.size })

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.x_x_x_large_padding)),
        pageSpacing = dimensionResource(R.dimen.large_padding)
    ) { page ->
        homeMovieModels[page].movieDominantColor?.let { dominantColor ->
            BannerMovieItem(
                modifier = modifier.carouselTransition(page, pagerState),
                bannerMovie = homeMovieModels[page],
                page = page,
                pagerState = pagerState,
                isSelected = pagerState.currentPage == page,
                dominantColor = dominantColor
            )
        }
    }
}

@Composable
fun BannerMovieItem(
    modifier: Modifier = Modifier,
    bannerMovie: HomeMovieModel,
    page: Int,
    pagerState: PagerState,
    isSelected: Boolean,
    dominantColor: Color
) {
    val animatedElevation by animateDpAsState(
        targetValue = if (isSelected) 4.dp else 0.dp, // Higher elevation for selected item
        animationSpec = tween(durationMillis = 500), label = ""
    )

    val animatedStrokeWidth by animateDpAsState(
        targetValue = if (isSelected && dominantColor != Color.White) 12.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    var composableSize by remember { mutableStateOf(Size.Zero) }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    // Gradient for the moving border
    val animatedBrush = Brush.linearGradient(
        colors = listOf(
            dominantColor.copy(alpha = 2F),
            dominantColor.copy(alpha = 0.5f),
            dominantColor.copy(alpha = 2F),
        ),
        start = Offset(0f, 0f),
        end = Offset(
            animatedOffset * composableSize.width,
            animatedOffset * composableSize.height
        )
    )
    Card(
        elevation = CardDefaults.cardElevation(animatedElevation),// Dynamic elevation
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                val size = coordinates.size
                composableSize = Size(size.width.toFloat(), size.height.toFloat())
            }
            .drawBehind {
                if (isSelected) {
                    val strokeWidth = animatedStrokeWidth.toPx()
                    drawRoundRect(
                        brush = animatedBrush,
                        size = size,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                        cornerRadius = CornerRadius(16.dp.toPx())
                    )
                }
            }
    ) {
        MovieImage(
            modifier = Modifier
                .fillMaxSize()
                .blurTransition(page, pagerState)
                .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.x_small_border))),
            imageUrl = bannerMovie.moviePosterImageUrl,
            contentScale = ContentScale.FillBounds
        )
    }
}
