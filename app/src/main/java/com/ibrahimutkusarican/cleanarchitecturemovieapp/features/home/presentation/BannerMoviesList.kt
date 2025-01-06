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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.CoilHelper.loadBitmapFromImageUrl
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.blurTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.carouselTransition
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.extractDominantColor
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
fun BannerMoviesList(
    modifier: Modifier = Modifier, homeMovieModels: List<HomeMovieModel>
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { homeMovieModels.size })
    var dominantColor by remember { mutableStateOf(Color.White) }

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.x_x_x_large_padding)),
        pageSpacing = dimensionResource(R.dimen.large_padding)
    ) { page ->
        val context = LocalContext.current
        LaunchedEffect(homeMovieModels[page]) {
            val bitmap = loadBitmapFromImageUrl(homeMovieModels[page].moviePosterImageUrl, context)
            dominantColor = bitmap?.extractDominantColor() ?: Color.White
        }
        BannerMovieItem(
            modifier = modifier.carouselTransition(page, pagerState),
            bannerMovie = homeMovieModels[page],
            page = page,
            pagerState = pagerState,
            isSelected = pagerState.currentPage == page,
            shadowColor = dominantColor
        )
    }
}

@Composable
fun BannerMovieItem(
    modifier: Modifier = Modifier,
    bannerMovie: HomeMovieModel,
    page: Int,
    pagerState: PagerState,
    isSelected: Boolean,
    shadowColor: Color
) {
    val animatedElevation by animateDpAsState(
        targetValue = if (isSelected) 8.dp else 0.dp, // Higher elevation for selected item
        animationSpec = tween(durationMillis = 500), label = ""
    )

    val animatedStrokeWidth by animateDpAsState(
        targetValue = if (isSelected && shadowColor != Color.White) 20.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    var composableSize by remember { mutableStateOf(Size.Zero) }

    val infiniteTransition = rememberInfiniteTransition()
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
            shadowColor,
            shadowColor.copy(alpha = 0.5f),
            shadowColor
        ),
        start = Offset(0f, 0f), // Horizontal animation
        end = Offset(
            animatedOffset * composableSize.width,
            animatedOffset * composableSize.height
        ) // Vertical animation
    )
    Card(
        elevation = CardDefaults.cardElevation(animatedElevation),// Dynamic elevation
        shape = RoundedCornerShape(16.dp), // Match the border shape
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                val size = coordinates.size // IntSize in pixels
                composableSize = Size(size.width.toFloat(), size.height.toFloat())
            }// Space for the border
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
            } // Add space around the card
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
