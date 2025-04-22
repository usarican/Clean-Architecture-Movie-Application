package com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun MovieImage(
    modifier : Modifier = Modifier,
    imageUrl : String?,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    AsyncImage(
        modifier = modifier.fillMaxSize(),
        model = imageUrl,
        contentDescription = "AsyncImage",
        contentScale = contentScale,
    )
}




