package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import android.content.Context
import androidx.annotation.StringRes
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.data.UserSettingsDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class StringProvider @Inject constructor(
    private val context: Context
) {
    fun getStringFromResource(@StringRes id: Int): String =
        getLocalizedContext().getString(id)

    fun getStringFromResource(@StringRes id: Int, vararg formatArgs: Any): String =
        getLocalizedContext().getString(id, *formatArgs)

    private fun getLocalizedContext(): Context {
        val storedLanguage = runBlocking {
            UserSettingsDataStore(context).getLanguageCode().first()
        }
        return UserSettingsDataStore(context).applyLocale(storedLanguage)
    }

}