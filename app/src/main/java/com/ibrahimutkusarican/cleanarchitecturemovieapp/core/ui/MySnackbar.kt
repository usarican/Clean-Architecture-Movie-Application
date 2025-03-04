package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun MySnackBar(
    modifier: Modifier = Modifier,
    snackBarModel: MySnackBarModel = MySnackBarModel(
        title = "Information",
        message = "This is Information SnackBar",
        type = SnackBarType.SUCCESS
    ),
    actionLabel: String? = null,
    action: (() -> Unit)? = null,
    visible: Boolean = true
) {
    var visibility by remember { mutableStateOf(visible) }

    LaunchedEffect(true) {
        if (actionLabel.isNullOrEmpty()) {
            delay(5000)
        } else {
            delay(10000)
        }
        visibility = false
    }
    AnimatedVisibility(
        visible = visibility, modifier = modifier,
        enter = slideInVertically(
            animationSpec = spring(Spring.DampingRatioHighBouncy)
        ),
        exit = slideOutVertically()
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
                containerColor = snackBarModel.type.containerColor,
                contentColor = snackBarModel.type.onContainerColor
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
                    painterResource(snackBarModel.type.iconId),
                    contentDescription = snackBarModel.message,
                    modifier = Modifier.size(dimensionResource(R.dimen.error_icon_size)),
                    colorFilter = ColorFilter.tint(snackBarModel.type.lightColor)
                )
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = dimensionResource(R.dimen.small_padding))
                ) {
                    if (snackBarModel.title != null) {
                        Text(
                            text = snackBarModel.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = snackBarModel.type.onContainerColor,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 1,
                        )
                    }
                    Text(
                        text = snackBarModel.message ?: stringResource(R.string.error_message),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = snackBarModel.type.onContainerColor
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (action != null){
                    Text(
                        modifier = Modifier
                            .padding(end = dimensionResource(R.dimen.x_small_padding))
                            .clickable { action.invoke() },
                        text = actionLabel ?: stringResource(R.string.retry),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.scrim, fontWeight = FontWeight.Bold
                        ),
                    )
                }
            }
        }
    }
}

data class MySnackBarModel(
    val title : String?,
    val message : String?,
    val type : SnackBarType
)


enum class SnackBarType(
    val lightColor: Color,
    val containerColor: Color,
    val onContainerColor: Color,
    @DrawableRes val iconId : Int
) {
    SUCCESS(
        lightColor = Color(0xFF1E7D3A),
        containerColor = Color(0xFFD3F6E6),
        onContainerColor = Color(0xFF003214),
        iconId = R.drawable.ic_success_snack_bar
    ),
    INFO(
        lightColor = Color(0xFF005FCC),
        containerColor = Color(0xFFD5E3FF),
        onContainerColor = Color(0xFF001A3C),
        iconId = R.drawable.ic_info_snack_bar
    ),
    WARNING(
        lightColor = Color(0xFFDC7800),
        containerColor = Color(0xFFFFE7C2),
        onContainerColor = Color(0xFF3B2100),
        iconId = R.drawable.ic_warning_snack_bar
    ),
    ERROR(
        lightColor = Color(0xFFBA1A1A),
        containerColor = Color(0xFFFFDAD6),
        onContainerColor = Color(0xFF410002),
        iconId = R.drawable.ic_error_outline
    )
}


