package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
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
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.SNACK_BAR_WITH_ACTION_DELAY
import kotlinx.coroutines.delay

@Composable
@Preview(showBackground = true)
fun MySnackBar(
    modifier: Modifier = Modifier,
    snackBarModel: MySnackBarModel = MySnackBarModel(
        title = "Information",
        message = "This is Information SnackBar",
        actionLabel = null,
        type = SnackBarType.SUCCESS,
        action = {},
        onDismiss = {},
        clickActionDismiss = {}
    ),
    visible: Boolean = true,
    isDarkMode: Boolean = false,
) {
    var visibility by remember { mutableStateOf(visible) }
    val snackBarColors = snackBarModel.type.getColors(isDarkMode)

    LaunchedEffect(key1 = snackBarModel, key2 = snackBarModel.type, key3 = visibility) {
        delay(SNACK_BAR_WITH_ACTION_DELAY)
        visibility = false
        snackBarModel.onDismiss?.invoke()
    }
    AnimatedVisibility(
        visible = visibility, modifier = modifier,
        enter = slideInVertically(
            animationSpec = spring(Spring.DampingRatioHighBouncy)
        ),
        exit = slideOutVertically(
            targetOffsetY = {it}
        )
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
                containerColor = snackBarColors.containerColor,
                contentColor = snackBarColors.onContainerColor
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        visibility = false
                        snackBarModel.clickActionDismiss?.invoke()
                    }
                    .padding(dimensionResource(R.dimen.small_padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(snackBarColors.iconId),
                    contentDescription = snackBarModel.message,
                    modifier = Modifier.size(dimensionResource(R.dimen.error_icon_size)),
                    colorFilter = ColorFilter.tint(snackBarColors.lightColor)
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
                                color = snackBarColors.onContainerColor,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 1,
                        )
                    }
                    Text(
                        text = snackBarModel.message,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = snackBarColors.onContainerColor
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (snackBarModel.action != null){
                    Text(
                        modifier = Modifier
                            .padding(end = dimensionResource(R.dimen.x_small_padding))
                            .clickable { snackBarModel.action.invoke() },
                        text = snackBarModel.actionLabel ?: stringResource(R.string.retry),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.scrim, fontWeight = FontWeight.Bold
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun MySnackBarHost(hostState: SnackbarHostState, isDarkMode: Boolean) {
    SnackbarHost(hostState = hostState) { snackBarData ->
        val snackBarModel = snackBarData.visuals as MySnackBarModel

        val myModel = MySnackBarModel(
            title = snackBarModel.title,
            message = snackBarModel.message,
            type = snackBarModel.type,
            actionLabel = snackBarModel.actionLabel,
            duration = snackBarModel.duration,
            withDismissAction = snackBarModel.withDismissAction
        )

        MySnackBar(
            snackBarModel = myModel,
            isDarkMode = isDarkMode,
            visible = true
        )
    }
}

data class MySnackBarModel(
    val title : String?,
    val type : SnackBarType,
    val movieId : Int? = null,
    override val message : String,
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = if (actionLabel != null) SnackbarDuration.Long else SnackbarDuration.Short,
    override val withDismissAction: Boolean = false,
    val action: (() -> Unit)? = null,
    val onDismiss: (() -> Unit)? = null,
    val clickActionDismiss: (() -> Unit)? = null,
) : SnackbarVisuals


enum class SnackBarType(
    private val lightModeColors: SnackBarColors,
    private val darkModeColors: SnackBarColors
) {
    SUCCESS(
        lightModeColors = SnackBarColors(
            lightColor = Color(0xFF1E7D3A),
            containerColor = Color(0xFFD3F6E6),
            onContainerColor = Color(0xFF003214),
            iconId = R.drawable.ic_success_snack_bar
        ),
        darkModeColors = SnackBarColors(
            lightColor = Color(0xFF52C474),
            containerColor = Color(0xFF004D20),
            onContainerColor = Color(0xFFA8EABF),
            iconId = R.drawable.ic_success_snack_bar
        )
    ),
    INFO(
        lightModeColors = SnackBarColors(
            lightColor = Color(0xFF005FCC),
            containerColor = Color(0xFFD5E3FF),
            onContainerColor = Color(0xFF001A3C),
            iconId = R.drawable.ic_info_snack_bar
        ),
        darkModeColors = SnackBarColors(
            lightColor = Color(0xFF7ABFFF),
            containerColor = Color(0xFF002C6D),
            onContainerColor = Color(0xFFB3D4FF),
            iconId = R.drawable.ic_info_snack_bar
        )
    ),
    WARNING(
        lightModeColors = SnackBarColors(
            lightColor = Color(0xFFDC7800),
            containerColor = Color(0xFFFFE7C2),
            onContainerColor = Color(0xFF3B2100),
            iconId = R.drawable.ic_warning_snack_bar
        ),
        darkModeColors = SnackBarColors(
            lightColor = Color(0xFFFFAB40),
            containerColor = Color(0xFF4E2600),
            onContainerColor = Color(0xFFFFD8A8),
            iconId = R.drawable.ic_warning_snack_bar
        )
    ),
    ERROR(
        lightModeColors = SnackBarColors(
            lightColor = Color(0xFFBA1A1A),
            containerColor = Color(0xFFFFDAD6),
            onContainerColor = Color(0xFF410002),
            iconId = R.drawable.ic_error_outline
        ),
        darkModeColors = SnackBarColors(
            lightColor = Color(0xFFFF7676),
            containerColor = Color(0xFF690005),
            onContainerColor = Color(0xFFFFB4A9),
            iconId = R.drawable.ic_error_outline
        )
    );

    fun getColors(isDarkMode: Boolean): SnackBarColors {
        return if (isDarkMode) darkModeColors else lightModeColors
    }
}

data class SnackBarColors(
    val lightColor: Color,
    val containerColor: Color,
    val onContainerColor: Color,
    @DrawableRes val iconId: Int
)

