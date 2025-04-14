package com.iusarican.convention

import com.android.build.api.dsl.CommonExtension
import com.iusarican.config.AppConfig
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = AppConfig.COMPILE_SDK

        defaultConfig {
            minSdk = AppConfig.MIN_SDK
        }

        compileOptions {
            sourceCompatibility = AppConfig.JAVA_VERSION
            targetCompatibility = AppConfig.JAVA_VERSION
        }
    }

    configureKotlin()
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = AppConfig.JAVA_VERSION.toString()
        }
    }
}