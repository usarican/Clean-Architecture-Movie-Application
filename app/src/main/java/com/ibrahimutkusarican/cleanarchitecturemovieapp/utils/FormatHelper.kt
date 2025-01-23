package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class FormatHelper @Inject constructor() {

    @SuppressLint("NewApi")
    fun formatReleaseDate(dateString: String, language: String): String {
        try {
            val parsedDate = LocalDate.parse(dateString)

            // Determine the Locale based on the language
            val locale = when (language.lowercase()) {
                "turkish", "tr" -> Locale("tr", "TR") // Turkish
                "english", "en" -> Locale("en", "US") // English
                else -> Locale.getDefault() // Fallback to system default
            }

            // Create formatter based on the locale
            val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", locale)

            // Format the date
            return parsedDate.format(formatter)
        } catch (e: Exception) {
            e.printStackTrace()
            return Constants.EMPTY_STRING
        }
    }

    @SuppressLint("NewApi")
    fun formatMovieReleaseDateToYear(dateString: String, language: String): String {
        return try {
            val parsedDate = LocalDate.parse(dateString)
            val locale = when (language.lowercase()) {
                "turkish", "tr" -> Locale("tr", "TR") // Turkish
                "english", "en" -> Locale("en", "US") // English
                else -> Locale.getDefault() // Fallback to system default
            }
            parsedDate.format(DateTimeFormatter.ofPattern("yyyy", locale))
        } catch (e: Exception) {
            e.printStackTrace()
            return Constants.EMPTY_STRING
        }
    }

    fun formatRuntime(runtimeInMinutes: Int): String {
        if (runtimeInMinutes <= 0) {
            return Constants.EMPTY_STRING
        }

        val hours = runtimeInMinutes / 60
        val minutes = runtimeInMinutes % 60

        return if (hours > 0) {
            "${hours}h ${minutes}m"
        } else {
            "${minutes}m"
        }
    }
}