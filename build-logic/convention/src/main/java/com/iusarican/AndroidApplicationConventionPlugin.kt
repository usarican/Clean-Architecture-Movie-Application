package com.iusarican

import com.android.build.api.dsl.ApplicationExtension
import com.iusarican.config.AppConfig
import com.iusarican.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) : Unit = with(project) {
        with(pluginManager){
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }
        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = AppConfig.TARGET_SDK
            defaultConfig.minSdk = AppConfig.MIN_SDK

            buildTypes {
                release {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
        }
    }
}