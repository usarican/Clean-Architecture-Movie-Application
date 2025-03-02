package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R

@Composable
@Preview(showBackground = true)
fun EmptyScreen(
    modifier: Modifier = Modifier,
    emptyScreenType: EmptyScreenType = EmptyScreenType.SEARCH,
    seeExploreAction: () -> Unit = {},
    visibility: Boolean = true
) {
    AnimatedVisibility(modifier = modifier.fillMaxSize(), visible = visibility) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EmptyImage(
                modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding)),
                emptyScreenType = emptyScreenType

            )
            EmptyTitleText(
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.small_padding)),
                emptyScreenType = emptyScreenType
            )
            EmptyContextText(
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.large_padding)),
                emptyScreenType = emptyScreenType

            )
            if (emptyScreenType.showExploreButton()) {
                GoToExploreButton(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.medium_padding)),
                    onClick = seeExploreAction
                )
            }
        }
    }
}

@Composable
private fun EmptyImage(
    modifier: Modifier = Modifier,
    emptyScreenType: EmptyScreenType = EmptyScreenType.WATCH_LIST,
) {
    val vectorImage = when (emptyScreenType) {
        EmptyScreenType.FAVORITE -> R.drawable.ic_empty_favorite
        EmptyScreenType.WATCH_LIST -> R.drawable.ic_empty_watch_list
        EmptyScreenType.SEARCH -> R.drawable.ic_empty_search
        EmptyScreenType.REVIEWS -> R.drawable.ic_empty_reviews
        EmptyScreenType.TRAILERS -> R.drawable.ic_empty_trailers
        EmptyScreenType.FILTER -> R.drawable.ic_empty_filter
    }
    Image(
        painter = painterResource(vectorImage),
        contentDescription = emptyScreenType.name,
        modifier = modifier
            .fillMaxHeight(0.3f)
            .fillMaxWidth(0.5f),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
    )
}

@Composable
private fun EmptyTitleText(
    modifier: Modifier = Modifier,
    emptyScreenType: EmptyScreenType = EmptyScreenType.WATCH_LIST,
) {
    val titleText = when (emptyScreenType) {
        EmptyScreenType.FAVORITE -> stringResource(R.string.empty_screen_title_favorite)
        EmptyScreenType.WATCH_LIST -> stringResource(R.string.empty_screen_title_watch_list)
        EmptyScreenType.SEARCH -> stringResource(R.string.empty_screen_title_search)
        EmptyScreenType.REVIEWS -> stringResource(R.string.empty_screen_title_reviews)
        EmptyScreenType.TRAILERS -> stringResource(R.string.empty_screen_title_trailers)
        EmptyScreenType.FILTER -> stringResource(R.string.empty_screen_title_filter)
    }
    Text(
        modifier = modifier,
        text = titleText,
        style = MaterialTheme.typography.titleLarge.copy(
            color = MaterialTheme.colorScheme.primary
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun EmptyContextText(
    modifier: Modifier = Modifier,
    emptyScreenType: EmptyScreenType = EmptyScreenType.WATCH_LIST,
) {
    val contextText = when (emptyScreenType) {
        EmptyScreenType.FAVORITE -> stringResource(R.string.empty_screen_description_favorite)
        EmptyScreenType.WATCH_LIST -> stringResource(R.string.empty_screen_description_watch_list)
        EmptyScreenType.SEARCH -> stringResource(R.string.empty_screen_description_search)
        EmptyScreenType.REVIEWS -> stringResource(R.string.empty_screen_description_reviews)
        EmptyScreenType.TRAILERS -> stringResource(R.string.empty_screen_description_trailers)
        EmptyScreenType.FILTER -> stringResource(R.string.empty_screen_description_filter)
    }
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.large_padding)),
        text = contextText,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun GoToExploreButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.dp_64)),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.small_border))
    ) {
        Text(
            text = stringResource(R.string.explore_button),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}

enum class EmptyScreenType {
    FAVORITE, WATCH_LIST, SEARCH, REVIEWS, TRAILERS,FILTER;

    fun showExploreButton(): Boolean = this == FAVORITE || this == WATCH_LIST
}