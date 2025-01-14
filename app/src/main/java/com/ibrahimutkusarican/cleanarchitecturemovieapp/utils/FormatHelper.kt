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
}