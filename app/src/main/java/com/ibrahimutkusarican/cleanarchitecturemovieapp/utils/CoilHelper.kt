package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import android.content.Context
import coil3.Bitmap
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap

object CoilHelper {
    suspend fun loadBitmapFromImageUrl(imageUrl: String?, context: Context): Bitmap? {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false) // Disable hardware bitmaps to ensure we can extract colors
            .build()

        return try {
            val result = (imageLoader.execute(request) as? SuccessResult)
            result?.image?.toBitmap()
        } catch (e: Exception) {
            null
        }
    }
}