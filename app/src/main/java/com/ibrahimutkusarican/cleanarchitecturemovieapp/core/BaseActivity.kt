package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.activity.ComponentActivity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.LocaleManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
open class BaseActivity : ComponentActivity() {


    override fun attachBaseContext(newBase: Context?) {
        val localizedContext = newBase?.let { context ->
            val storedLanguage = runBlocking {
                LocaleManager(context).getStoredLanguage().first()
            }
            LocaleManager(context).applyLocale(storedLanguage)
        }
        super.attachBaseContext(localizedContext ?: newBase)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("BaseActivity", "onConfigurationChanged: $newConfig")
    }
}