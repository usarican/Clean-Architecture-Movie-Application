package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.core.content.FileProvider
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.request.crossfade
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class ImageShareHelper(
    @ApplicationContext private val context : Context,
) {

    suspend fun getShareableImageUri(imageUrl: String): Uri? = withContext(Dispatchers.IO) {
        try {
            // Use Coil to fetch the bitmap
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .allowHardware(false) // Required for bitmap access
                .build()

            val imageLoader = ImageLoader.Builder(context)
                .crossfade(true)
                .build()

            val result = (imageLoader.execute(request) as SuccessResult).image
            val bitmap = (result as BitmapDrawable).bitmap

            // Save bitmap to cache directory
            val cachePath = File(context.cacheDir, "images")
            cachePath.mkdirs()

            val imagePath = File(cachePath, "shared_image.jpg")
            val stream = FileOutputStream(imagePath)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.close()

            // Return content URI using FileProvider
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                imagePath
            )
        } catch (e: Exception) {
            null
        }
    }
}