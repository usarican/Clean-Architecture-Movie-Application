package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseActivity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.EventListener
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.AppTheme
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.LocaleManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var localeManager: LocaleManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLanguageUpdate()
        observeViewModel()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            ),
        )
        setContent {
            val userSettings = mainViewModel.userSetting.collectAsStateWithLifecycle()
            AppTheme(
                darkTheme = userSettings.value.isDarkModeEnabled
            ) {
                MainScreen(mainViewModel)
            }
        }
    }

    private fun observeViewModel() {
        EventListener.collectOneEvent<MyEvent.RestartApp>(lifecycleScope) {
            restartApp()
        }

    }

    private fun checkLanguageUpdate() {
        runBlocking {
            if (localeManager.getLanguageChangeFlag().first()){
                mainViewModel.languageChanged()
            }
        }
    }

    private fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}