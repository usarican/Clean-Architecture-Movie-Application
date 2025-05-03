package com.iusarican

import androidx.annotation.StringRes
import com.iusarican.model.R

enum class Language(
    val languageCode: String,
    @StringRes val languageText: Int
) {
    TR(languageCode = "tr", languageText = R.string.language_tr),
    EN(languageCode = "en", languageText = R.string.language_en);

    companion object {
        fun fromLanguageCode(languageCode: String): Language {
            return entries.find { it.languageCode == languageCode } ?: EN
        }
    }
}