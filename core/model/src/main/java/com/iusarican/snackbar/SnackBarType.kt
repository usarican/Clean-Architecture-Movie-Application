package com.iusarican.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.iusarican.model.R

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
