package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.main.presentation

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.iusarican.common.base.BaseActivity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.EventListener
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.languageChanged()
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

    private fun setScreenOrientation(isLandscape: Boolean) {
        requestedOrientation = if (isLandscape) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    private fun observeViewModel() {
        EventListener.collectOneEvent<MyEvent.RestartApp>(lifecycleScope) {
            restartApp()
        }
        EventListener.collectOneEvent<MyEvent.RotateScreenEvent>(lifecycleScope){ event ->
            setScreenOrientation(event.isLandScape)
        }

    }

    private fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}