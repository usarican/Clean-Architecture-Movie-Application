package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import kotlinx.coroutines.delay

@Composable
@Preview(showBackground = true)
fun ErrorSnackBar(
    modifier: Modifier = Modifier,
    errorMessage: String? = "Oh god!",
    actionLabel: String = stringResource(R.string.retry),
    action: () -> Unit = {},
    visible: Boolean = true
) {
    var visibility by remember { mutableStateOf(visible) }

    LaunchedEffect(true) {
        if (actionLabel.isEmpty()) {
            delay(5000)
        } else {
            delay(10000)
        }
        visibility = false
    }
    AnimatedVisibility(
        visible = visibility, modifier = modifier
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(R.dimen.small_padding),
                    vertical = dimensionResource(R.dimen.x_small_padding)
                ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.small_border)),
            elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.small_padding)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { visibility = false }
                    .padding(dimensionResource(R.dimen.small_padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.ic_error_outline),
                    contentDescription = errorMessage,
                    modifier = Modifier.size(dimensionResource(R.dimen.error_icon_size)),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
                )
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = dimensionResource(R.dimen.small_padding))
                ) {
                    Text(
                        text = stringResource(R.string.error_snackbar_title),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                    )
                    Text(
                        text = errorMessage ?: stringResource(R.string.error_message),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onErrorContainer
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(end = dimensionResource(R.dimen.x_small_padding))
                        .clickable { action() },
                    text = actionLabel,
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = MaterialTheme.colorScheme.scrim, fontWeight = FontWeight.Bold
                    ),
                )
            }
        }
    }
}

