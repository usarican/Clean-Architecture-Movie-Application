package com.iusarican

import com.iusarican.convention.implementation
import com.iusarican.convention.ksp
import com.iusarican.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.withPlugin("com.android.base") {
                pluginManager.apply("androidx.room")
                pluginManager.apply("com.google.devtools.ksp")

                dependencies {
                    implementation(libs.findLibrary("room-runtime").get())
                    implementation(libs.findLibrary("room-ktx").get())
                    ksp(libs.findLibrary("room-compiler").get())
                }
            }
        }
    }
}