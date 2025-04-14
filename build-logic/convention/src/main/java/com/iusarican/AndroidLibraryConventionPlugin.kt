package com.iusarican

import com.android.build.api.dsl.LibraryExtension
import com.iusarican.config.AppConfig
import com.iusarican.convention.configureKotlinAndroid
import com.iusarican.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        with(pluginManager) {
            apply(libs.findPlugin("android-library").get())
            apply(libs.findPlugin("kotlin-android").get())
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            lint.targetSdk = AppConfig.TARGET_SDK
        }
    }
}