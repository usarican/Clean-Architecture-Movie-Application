package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import android.content.Context
import androidx.activity.ComponentActivity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.LocaleManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity : ComponentActivity() {


    override fun attachBaseContext(newBase: Context?) {
        val localizedContext = newBase?.let { context ->
            val storedLanguage = runBlocking {
                LocaleManager(context).getStoredLanguage().firstOrNull() ?: "tr" // Fetch language safely
            }
            LocaleManager(context).applyLocale(storedLanguage)
        }
        super.attachBaseContext(localizedContext ?: newBase)
    }
}