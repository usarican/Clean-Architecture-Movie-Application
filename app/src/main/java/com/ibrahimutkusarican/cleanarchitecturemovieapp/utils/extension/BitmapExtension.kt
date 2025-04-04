package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette

fun Bitmap.extractDominantColor() : Color {
    val palette = Palette.from(this).generate()
    val dominantColor = palette.getVibrantColor(Color.White.toArgb())
    return Color(dominantColor)
}