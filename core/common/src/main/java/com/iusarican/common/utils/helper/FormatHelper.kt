package com.iusarican.common.utils.helper

import android.annotation.SuppressLint
import com.iusarican.Language
import com.iusarican.common.utils.Constants
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Currency
import java.util.Locale
import javax.inject.Inject

class FormatHelper @Inject constructor() {

    @SuppressLint("NewApi")
    fun formatReleaseDate(dateString: String, language: Language): String {
        try {
            val parsedDate = LocalDate.parse(dateString)

            val locale = when (language) {
                Language.TR -> Locale("tr", "TR")
                Language.EN -> Locale("en", "US")
            }

            val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", locale)

            return parsedDate.format(formatter)
        } catch (e: Exception) {
            e.printStackTrace()
            return Constants.EMPTY_STRING
        }
    }

    @SuppressLint("NewApi")
    fun formatMovieReleaseDateToYear(dateString: String, language: Language): String {
        return try {
            val parsedDate = LocalDate.parse(dateString)
            val locale = when (language) {
                Language.TR -> Locale("tr", "TR")
                Language.EN -> Locale("en", "US")
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

    fun formatMoney(amount: Long, language: Language): String {
        val locale = when (language) {
            Language.TR -> Locale("tr", "TR")
            Language.EN -> Locale("en", "US")
        }

        val currencySymbol = Currency.getInstance(locale).symbol

        val newAmount = if (language == Language.TR) amount * 40 else amount

        if (newAmount <= 0) {
            return "0 $currencySymbol"
        }

        val suffix: String
        val value: Double

        when {
            newAmount >= 1_000_000_000L -> {
                suffix = "B"
                value = newAmount / 1_000_000_000.0
            }

            newAmount >= 1_000_000L -> {
                suffix = "M"
                value = newAmount / 1_000_000.0
            }

            newAmount >= 1_000L -> {
                suffix = "K"
                value = newAmount / 1_000.0
            }

            else -> {
                return "$newAmount $currencySymbol"
            }
        }

        return if (value % 1.0 == 0.0) {
            String.format(locale, "%.0f%s %s", value, suffix, currencySymbol)
        } else {
            String.format(locale, "%.1f%s %s", value, suffix, currencySymbol)
        }
    }

    fun formatVoteAverageAndVoteCount(voteAverage: Double, voteCount: Int,language: Language): String {
        val locale = when (language) {
            Language.TR -> Locale("tr", "TR")
            Language.EN -> Locale("en", "US")
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