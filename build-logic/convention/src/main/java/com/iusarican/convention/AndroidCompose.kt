package com.iusarican.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("kotlin").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            implementation(platform(bom))
            androidTestImplementation(platform(bom))
            implementation(libs.findLibrary("androidx-material3").get())
            debugImplementation(libs.findLibrary("androidx-ui-tooling").get())
            implementation(libs.findLibrary("androidx-ui-tooling-preview").get())
            implementation(libs.findLibrary("androidx-ui-text-google-fonts").get())
            implementation(libs.findLibrary("androidx-ui-graphics").get())
            implementation(libs.findLibrary("androidx-ui").get())
            implementation(libs.findLibrary("androidx-constraintlayout-compose").get())
        }
    }
}