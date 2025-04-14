package com.iusarican

import com.android.build.api.dsl.LibraryExtension
import com.iusarican.config.AppConfig
import com.iusarican.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = AppConfig.TARGET_SDK
        }
    }
}