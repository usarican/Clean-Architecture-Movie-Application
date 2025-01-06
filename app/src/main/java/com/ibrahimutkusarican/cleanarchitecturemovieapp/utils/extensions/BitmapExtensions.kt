package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette

fun Bitmap.extractDominantColor() : Color {
    val palette = Palette.from(this).generate()
    val dominantColor = palette.getDominantColor(Color.White.toArgb())
    return Color(dominantColor)
}