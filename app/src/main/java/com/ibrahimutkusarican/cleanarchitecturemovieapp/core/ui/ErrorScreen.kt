package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieException
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@Preview(showBackground = true)
fun ErrorScreen(
    modifier: Modifier = Modifier,
    exception: MovieException = MovieException.NoInternetException("Error"),
    tryAgainOnClickAction: () -> Unit = {}
) {
    var visibility by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        delay(300)
        visibility = true
    }
    AnimatedVisibility(modifier = modifier.fillMaxSize(), visible = visibility) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ErrorImage(
                modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding)),
                exception = exception
            )
            ErrorTitleText(
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.small_padding)),
                exception = exception
            )
            ErrorContentText(
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.large_padding)),
                exception = exception
            )
            TryAgainButton(
                modifier = Modifier.padding(top = dimensionResource(R.dimen.medium_padding)),
                onClick = tryAgainOnClickAction
            )
        }
    }

}

@Composable
fun ErrorTitleText(modifier: Modifier = Modifier, exception: MovieException) {
    val text = when (exception) {
        is MovieException.CoilHttpException -> stringResource(R.string.error_title_coil)
        is MovieException.GeneralException -> stringResource(R.string.error_title_general)
        is MovieException.GeneralHttpException -> stringResource(
            R.string.error_title_http, exception.code
        )

        is MovieException.InternalServerErrorException -> stringResource(R.string.error_title_server)
        is MovieException.NoInternetException -> stringResource(R.string.error_title_no_internet)
        is MovieException.NotFoundException -> stringResource(R.string.error_title_not_found)
        is MovieException.UnauthorizedException -> stringResource(R.string.error_title_unauthorized)
    }
    Text(
        modifier = modifier, text = text, style = MaterialTheme.typography.titleLarge.copy(
            color = MaterialTheme.colorScheme.error
        )
    )
}

@Composable
fun ErrorContentText(modifier: Modifier = Modifier, exception: MovieException) {
    val text = when (exception) {
        is MovieException.CoilHttpException -> stringResource(R.string.error_content_coil)
        is MovieException.GeneralException -> stringResource(R.string.error_message)
        is MovieException.GeneralHttpException -> stringResource(
            R.string.error_content_http, exception.message ?: Constants.EMPTY_STRING
        )

        is MovieException.InternalServerErrorException -> stringResource(R.string.error_content_server)
        is MovieException.NoInternetException -> stringResource(R.string.error_content_no_internet)
        is MovieException.NotFoundException -> stringResource(R.string.error_content_not_found)
        is MovieException.UnauthorizedException -> stringResource(R.string.error_content_unauthorized)
    }
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.scrim
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TryAgainButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    val rotationZ = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            repeat(3) {
                rotationZ.animateTo(
                    targetValue = -2f, // Rotate counter-clockwise
                    animationSpec = tween(durationMillis = 50, easing = LinearEasing)
                )
                rotationZ.animateTo(
                    targetValue = 2f, // Rotate clockwise
                    animationSpec = tween(durationMillis = 50, easing = LinearEasing)
                )
            }
            rotationZ.animateTo(
                targetValue = 0f, // Return to original position
                animationSpec = tween(durationMillis = 50, easing = LinearEasing)
            )
            delay(2000)
        }
    }
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.dp_64))
            .graphicsLayer(rotationZ = rotationZ.value),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.small_border))
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(R.string.try_again),
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.onErrorContainer
            ),
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun ErrorImage(modifier: Modifier = Modifier, exception: MovieException) {
    val vectorImage = when (exception) {
        is MovieException.CoilHttpException -> R.drawable.ic_palette
        is MovieException.GeneralException, is MovieException.GeneralHttpException, is MovieException.InternalServerErrorException -> R.drawable.ic_very_sad

        is MovieException.NoInternetException -> R.drawable.ic_no_internet_connection
        is MovieException.NotFoundException -> R.drawable.ic_search_global
        is MovieException.UnauthorizedException -> R.drawable.ic_signuture
    }

    val scale = remember { Animatable(0.6f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(key1 = exception) {
        launch {
            scale.animateTo(
                targetValue = 1f, animationSpec = repeatable(
                    iterations = 5,
                    animation = tween(durationMillis = 800, easing = LinearOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
        launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
        }

    }


    Image(
        painter = painterResource(vectorImage),
        contentDescription = exception.message,
        modifier = modifier
            .fillMaxHeight(0.3f)
            .fillMaxWidth(0.5f)
            .graphicsLayer(
                scaleX = scale.value, scaleY = scale.value, alpha = alpha.value
            ),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
    )
}
