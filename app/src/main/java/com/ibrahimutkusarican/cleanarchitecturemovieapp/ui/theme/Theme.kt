package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.AppTypography
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.backgroundDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.backgroundDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.backgroundDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.backgroundLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.backgroundLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.backgroundLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1ContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1ContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1ContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1ContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1ContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1ContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1Dark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1DarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1DarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1Light
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1LightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.customColor1LightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.errorLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseOnSurfaceDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseOnSurfaceDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseOnSurfaceLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseOnSurfaceLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseOnSurfaceLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inversePrimaryDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inversePrimaryDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inversePrimaryDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inversePrimaryLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inversePrimaryLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inversePrimaryLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseSurfaceDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseSurfaceDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseSurfaceDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseSurfaceLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseSurfaceLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.inverseSurfaceLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onBackgroundDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onBackgroundDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onBackgroundDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onBackgroundLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onBackgroundLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onBackgroundLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1ContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1ContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1ContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1ContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1ContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1ContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1Dark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1DarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1DarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1Light
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1LightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onCustomColor1LightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onErrorLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onPrimaryLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSecondaryLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceVariantDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceVariantDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceVariantDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceVariantLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceVariantLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onSurfaceVariantLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.onTertiaryLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineVariantDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineVariantDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineVariantDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineVariantLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineVariantLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.outlineVariantLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.primaryLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.scrimDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.scrimDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.scrimDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.scrimLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.scrimLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.scrimLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.secondaryLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceBrightDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceBrightDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceBrightDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceBrightLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceBrightLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceBrightLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighestDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighestDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighestLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighestLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerHighestLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowestDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowestDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowestLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowestLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceContainerLowestLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceDimDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceDimDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceDimDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceDimLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceDimLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceDimLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceVariantDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceVariantDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceVariantDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceVariantLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceVariantLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.surfaceVariantLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryContainerDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryContainerDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryContainerDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryContainerLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryContainerLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryContainerLightMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryDark
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryDarkHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryDarkMediumContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryLight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryLightHighContrast
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.tertiaryLightMediumContrast

@Immutable
data class ExtendedColorScheme(
    val customColor1: ColorFamily,
)

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

val extendedLight = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1Light,
  onCustomColor1Light,
  customColor1ContainerLight,
  onCustomColor1ContainerLight,
  ),
)

val extendedDark = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1Dark,
  onCustomColor1Dark,
  customColor1ContainerDark,
  onCustomColor1ContainerDark,
  ),
)

val extendedLightMediumContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1LightMediumContrast,
  onCustomColor1LightMediumContrast,
  customColor1ContainerLightMediumContrast,
  onCustomColor1ContainerLightMediumContrast,
  ),
)

val extendedLightHighContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1LightHighContrast,
  onCustomColor1LightHighContrast,
  customColor1ContainerLightHighContrast,
  onCustomColor1ContainerLightHighContrast,
  ),
)

val extendedDarkMediumContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1DarkMediumContrast,
  onCustomColor1DarkMediumContrast,
  customColor1ContainerDarkMediumContrast,
  onCustomColor1ContainerDarkMediumContrast,
  ),
)

val extendedDarkHighContrast = ExtendedColorScheme(
  customColor1 = ColorFamily(
  customColor1DarkHighContrast,
  onCustomColor1DarkHighContrast,
  customColor1ContainerDarkHighContrast,
  onCustomColor1ContainerDarkHighContrast,
  ),
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

