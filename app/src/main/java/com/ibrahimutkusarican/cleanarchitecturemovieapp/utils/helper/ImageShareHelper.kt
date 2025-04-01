package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import coil3.BitmapImage
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.crossfade
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageShareHelper(
    @ApplicationContext private val context : Context,
) {

    suspend fun getShareableImageUri(imageUrl: String?,movieTitle: String?): Uri? = withContext(Dispatchers.IO) {
        try {
            // Use Coil to fetch the bitmap
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .build()

            val imageLoader = ImageLoader.Builder(context)
                .crossfade(true)
                .build()

            val result = imageLoader.execute(request)
            if (result is SuccessResult) {
                val bitmap = (result.image as? BitmapImage)?.bitmap ?: return@withContext null

                // Create a filename based on the movie title if available
                val fileName = "movie_poster_${movieTitle?.replace(" ", "_") ?: System.currentTimeMillis()}.jpg"

                // Use MediaStore to save and get a shareable URI
                return@withContext shareImageUsingMediaStore(context, bitmap, fileName)
            }

            return@withContext null
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun shareImageUsingMediaStore(
        context: Context,
        bitmap: Bitmap,
        fileName: String = "shared_image_${System.currentTimeMillis()}.jpg"
    ): Uri? = withContext(Dispatchers.IO) {
        try {
            val contentValues = ContentValues().apply {
                // Set the file metadata
                put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")

                // For Android 10 and above, we can specify a relative path
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // Save to Pictures/YourAppName directory
                    put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/YourAppName")
                    // Mark as pending until we write the file
                    put(MediaStore.Images.Media.IS_PENDING, 1)
                }
            }

            // Insert the image into MediaStore
            val contentResolver = context.contentResolver
            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            uri?.let { imageUri ->
                // Write the bitmap data to the URI
                contentResolver.openOutputStream(imageUri)?.use { outputStream ->
                    // Compress the bitmap as JPEG with 90% quality
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
                }

                // For Android 10+, mark the image as not pending anymore
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    contentValues.clear()
                    contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                    contentResolver.update(imageUri, contentValues, null, null)
                }

                return@withContext imageUri
            }

            return@withContext null
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext null
        }
    }
}