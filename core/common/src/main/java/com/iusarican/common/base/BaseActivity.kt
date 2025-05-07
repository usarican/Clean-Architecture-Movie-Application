package com.iusarican.common.base

import android.content.Context
import androidx.activity.ComponentActivity
import com.iusarican.datastore.UserSettingsDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
open class BaseActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context?) {
        val localizedContext = newBase?.let { context ->
            val selectedLanguageCode = runBlocking {
                UserSettingsDataStore(context).getLanguageCode().first()
            }
            UserSettingsDataStore(context).applyLocale(selectedLanguageCode)
        }
        super.attachBaseContext(localizedContext)
    }
}