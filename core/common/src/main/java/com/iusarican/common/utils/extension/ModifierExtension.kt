package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

fun Modifier.carouselTransition(startValue : Float = 0.8F,page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation =
            lerp(
                start = startValue,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        alpha = transformation
        scaleY = transformation
    }

fun Modifier.blurTransition(page: Int, pagerState: PagerState): Modifier {
    val pageOffset =
        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

    return if (pageOffset > 0.5) {
        blur(15.dp)
    } else {
        this
    }
}

@Composable
fun Modifier.statusBarAndNavigationBarPadding() : Modifier =
    this.windowInsetsPadding(WindowInsets.statusBars.union(WindowInsets.navigationBars))