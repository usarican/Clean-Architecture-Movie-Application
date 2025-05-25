package com.iusarican

import com.android.build.api.dsl.LibraryExtension
import com.iusarican.convention.configureAndroidCompose
import com.iusarican.convention.implementation
import com.iusarican.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class CamaFeatureConventionPlugin : Plugin<Project>  {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {
                apply("cama.android.library")
                apply("cama.hilt")
                apply("cama.compose.library")
                apply("org.jetbrains.kotlin.plugin.serialization")
                val extension = extensions.getByType<LibraryExtension>()
                configureAndroidCompose(extension)
            }

            dependencies {
                implementation(project(":core:ui"))
                implementation(project(":core:common"))

                implementation(libs.findLibrary("androidx-hilt-navigation-compose").get())
                implementation(libs.findLibrary("androidx-compose-navigation").get())
                implementation(libs.findLibrary("lifecycle.runtime.compose").get())
                implementation(libs.findLibrary("lifecycle.viewmodel.compose").get())
                implementation(libs.findLibrary("kotlinx-serialization-json").get())
            }
        }
    }
}