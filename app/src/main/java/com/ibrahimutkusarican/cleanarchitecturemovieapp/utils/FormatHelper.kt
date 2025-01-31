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

    fun formatMoney(amount: Long, language: String): String {
        if (amount <= 0) {
            return "0 $"
        }

        val suffix: String
        val value: Double

        when {
            amount >= 1_000_000_000L -> {
                suffix = "B"  // Billions
                value = amount / 1_000_000_000.0
            }

            amount >= 1_000_000 -> {
                suffix = "M"  // Millions
                value = amount / 1_000_000.0
            }

            amount >= 1_000 -> {
                suffix = "K"  // Thousands
                value = amount / 1_000.0
            }

            else -> {
                // Less than 1,000 -> just return "500 $"
                return "$amount $"
            }
        }

        val locale = when (language.lowercase()) {
            "turkish", "tr" -> Locale("tr", "TR") // Turkish
            "english", "en" -> Locale("en", "US") // English
            else -> Locale.getDefault() // Fallback to system default
        }

        // Check if we need decimals
        return if (value % 1.0 == 0.0) {
            // If value is an integer, e.g. 422.0 -> "422M $"
            String.format(locale, "%.0f%s $", value, suffix, locale)
        } else {
            // Otherwise, keep one decimal digit, e.g. 422.4 -> "422.4M $"
            String.format(locale, "%.1f%s $", value, suffix)
        }
    }

    fun formatVoteAverage(voteAverage: Double,language: String) : String {
        val locale = when (language.lowercase()) {
            "turkish", "tr" -> Locale("tr", "TR") // Turkish
            "english", "en" -> Locale("en", "US") // English
            else -> Locale.getDefault() // Fallback to system default
        }
        return String.format(locale,"%.1f", voteAverage).removeSuffix(".0")
    }

    fun formatVoteAverageAndVoteCount(voteAverage: Double, voteCount: Int,language: String): String {
        val locale = when (language.lowercase()) {
            "turkish", "tr" -> Locale("tr", "TR") // Turkish
            "english", "en" -> Locale("en", "US") // English
            else -> Locale.getDefault() // Fallback to system default
        }
        val avgString = String.format(locale,"%.1f", voteAverage).removeSuffix(".0")
        val baseOutput = "$avgString / 10"

        return if (voteCount < 1000) {
            "$baseOutput ($voteCount)"
        } else {
            val countInThousands = voteCount / 1000.0
            val countString = String.format(locale,"%.1f", countInThousands).removeSuffix(".0") + "K"
            "$baseOutput ($countString)"
        }
    }
}